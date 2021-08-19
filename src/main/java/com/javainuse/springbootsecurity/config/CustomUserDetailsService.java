package com.javainuse.springbootsecurity.config;

import com.javainuse.springbootsecurity.model.DAOUser;
import com.javainuse.springbootsecurity.model.UserDTO;
import com.javainuse.springbootsecurity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Arrays;
import java.util.List;

/**
 * The type Custom user details service.
 */
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("START CustomUserDetailsService.loadByUserName");

        List<SimpleGrantedAuthority> roles = null;


        DAOUser user = userDao.findByUsername(username);
        if (user != null) {
            roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
            log.info("END CustomUserDetailsService.loadByUserName");
            return new User(user.getUsername(), user.getPassword(), roles);
        }
        throw new UsernameNotFoundException("User not found with the name " + username);


    }

    /**
     * Save dao user.
     *
     * @param user the user
     * @return the dao user
     */
    public ResponseEntity<Object> save(UserDTO user) {
        log.info("START CustomUserDetailsService.save");
        DAOUser newUser = new DAOUser();
        ResponseEntity response;
        try {

            newUser.setUsername(user.getUsername());
            newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
            newUser.setRole(user.getRole());
            userDao.save(newUser);
            response = new ResponseEntity("Usuario creado\n" + userDao.toString(), HttpStatus.CREATED);
        } catch (EntityExistsException entityExistsException) {
            log.info("Ya existe un usuario creado con esta cuenta");
            response = new ResponseEntity("Ya existe el usuario " + user.getUsername(), HttpStatus.CONFLICT);
        }
        log.info("END CustomUserDetailsService.save");
        return response;
    }

}
