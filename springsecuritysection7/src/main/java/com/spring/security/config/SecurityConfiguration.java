package com.spring.security.config;

import com.spring.security.exception.CustomeAccessDeniedHandler;
import com.spring.security.exception.CustomeBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import javax.sql.DataSource;

@Configuration
@Profile("!prod")
public class SecurityConfiguration {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.sessionManagement(sm -> sm.invalidSessionUrl("/invalidSession").maximumSessions(2).maxSessionsPreventsLogin(true))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/contact", "/notices", "/register", "/error","/invalidSession").permitAll()
        );

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(httpBasicConfigurer -> httpBasicConfigurer.authenticationEntryPoint(new CustomeBasicAuthenticationEntryPoint()) );
//        http.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(new CustomeBasicAuthenticationEntryPoint())); // It's a global config
        http.exceptionHandling(eh -> eh.accessDeniedHandler(new CustomeAccessDeniedHandler()));

        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

    //This below bean is not needed as we have implemented our own custom UserDetailsService in the BankUserDetailsService class to load user/customer details from database based on userEmail
  /*  @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }*/

}
