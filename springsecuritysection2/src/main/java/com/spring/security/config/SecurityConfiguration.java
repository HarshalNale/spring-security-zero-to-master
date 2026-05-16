package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {


    //Permit all
/*    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(requests ->
                requests.anyRequest().permitAll());

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }*/


    //Deny all
  /*  @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(requests ->
                requests.anyRequest().denyAll());

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }*/


    //authenticate some URLs and permit some URLs
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(requests -> requests
                        .requestMatchers("/myAccount", "/myBalance","/myLoans","/myCards").authenticated()
                        .requestMatchers("/contact","/notices","/error").permitAll()
                );

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }


    //Disabled form login
  /*  @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/myAccount", "/myBalance","/myLoans","/myCards").authenticated()
                .requestMatchers("/contact","/notices","/error").permitAll()
        );

        http.formLogin(flc -> flc.disable());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }*/

    //Disabled http basic
  /*  @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/myAccount", "/myBalance","/myLoans","/myCards").authenticated()
                .requestMatchers("/contact","/notices","/error").permitAll()
        );

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(hbc -> hbc.disable());
        return http.build();
    }*/

    //Disabled form login and http basic
/*    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/myAccount", "/myBalance","/myLoans","/myCards").authenticated()
                .requestMatchers("/contact","/notices","/error").permitAll()
        );

        http.formLogin(flc -> flc.disable());
        http.httpBasic(hbc -> hbc.disable());
        return http.build();
    }*/
}
