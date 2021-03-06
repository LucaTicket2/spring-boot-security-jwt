package com.javainuse.springbootsecurity.controller;

import com.javainuse.springbootsecurity.config.CustomUserDetailsService;
import com.javainuse.springbootsecurity.config.JwtUtil;
import com.javainuse.springbootsecurity.model.AuthenticationRequest;
import com.javainuse.springbootsecurity.model.AuthenticationResponse;
import com.javainuse.springbootsecurity.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		log.info("START AuthenticationController.createAuthenticationToken");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			log.error("DISABLED USER");
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			log.error("INVALID CREDENTIALS");

			throw new Exception("INVALID_CREDENTIALS", e);
		}
		UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtUtil.generateToken(userdetails);
		log.info("END AuthenticationController.createAuthenticationToken");

		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		log.info("START AuthenticationController.saveUser");
		return ResponseEntity.ok(userDetailsService.save(user));
	}
}
