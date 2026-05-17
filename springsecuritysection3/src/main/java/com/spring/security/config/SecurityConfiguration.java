package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Configuration
public class SecurityConfiguration {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/contact", "/notices", "/error").permitAll()
        );

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

//    Scenario - 1
//    java.lang.IllegalArgumentException: Given that there is no default password encoder configured, each password must have a password encoding prefix. Please either prefix this password with '{noop}' or set a default password encoder in `DelegatingPasswordEncoder`.
 /*   @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user").password("12345").authorities("user").build();
        UserDetails admin = User.withUsername("admin").password("54321").authorities("admin").build();
        return new InMemoryUserDetailsManager(user, admin);
    }*/

//    Scenario - 2
    //1. using noop password encoders for user
    //2. using bcrypt password encoders for user with Bcrypt Hash password which is equal to 54321(password)
    /*   @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user").password("{noop}12345").authorities("user").build();
        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$f0LYOElURCVY/d2iBgWzXeuQJs7iu7CaLIL3BdVcb79X0Jy8DFRIu").authorities("admin").build();
        return new InMemoryUserDetailsManager(user, admin);
    }*/

//    Scenario - 3
    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user").password(passwordEncoder().encode("12345")).authorities("user").build();
        UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("54321")).authorities("admin").build();
        return new InMemoryUserDetailsManager(user, admin);
    }*/

 /*   @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//   Added below bean to test Scenario - 4
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        // Standard implementation relying on the Have I Been Pwned REST API
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

//    Scenario - 4
    //added some strong password to pass CompromisedPasswordChecker bean validation
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user").password(passwordEncoder().encode("UseR@12345")).authorities("user").build();
        UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("AdmiN@54321")).authorities("admin").build();
        return new InMemoryUserDetailsManager(user, admin);
    }


}
