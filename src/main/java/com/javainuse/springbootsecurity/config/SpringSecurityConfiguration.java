package com.javainuse.springbootsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The type Spring security configuration.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                // TODO: 20/08/2021 Listar eventos
                .antMatchers(AUTH_WHITELIST).permitAll()
                // TODO: 20/08/2021 Crear eventos
                .antMatchers("/hello_admin").hasRole("ADMIN")
                // TODO: 20/08/2021 getAllTicketsByUser
                .antMatchers("/hello_user").hasAnyRole("USER", "ADMIN")
                // TODO: 20/08/2021 en esta cadena se añadirian las que estan .permitAll().anyRequest().authenticated()
                .antMatchers("/authenticate", "/register",
                        "http://localhost:3333/purchase-ticket"
                ).permitAll().anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).
                and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


    }

}
