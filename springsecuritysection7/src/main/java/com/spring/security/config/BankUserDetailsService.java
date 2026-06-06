package com.spring.security.config;

import com.spring.security.entity.Customer;
import com.spring.security.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that implements Spring Security's {@link UserDetailsService} interface.
 *
 * <p>This class is responsible for loading user details from the database during the
 * authentication process. Spring Security uses this service to verify user credentials
 * when a user attempts to log in.</p>
 *
 * <p>Key Responsibilities:</p>
 * <ul>
 *   <li>Fetch customer details by email from the database</li>
 *   <li>Convert the {@link Customer} entity into Spring Security's {@link UserDetails}</li>
 *   <li>Provide user credentials (email, hashed password) and authorities (roles) to the
 *       AuthenticationManager</li>
 * </ul>
 *
 * <p>This implementation uses email as the username for authentication.</p>
 */
@Service
@AllArgsConstructor
public class BankUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    /**
     * Loads a user by their email (username) for authentication.
     *
     * @param username the email address used as username during login
     * @return UserDetails object containing user credentials and authorities
     * @throws UsernameNotFoundException if no user is found with the given email
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch customer from database using email
        Customer customer = customerRepository.findByEmail(username).orElseThrow(() -> new
                UsernameNotFoundException("User details not found for the user " + username));

        // Convert customer's role into Spring Security GrantedAuthority
        List<SimpleGrantedAuthority> simpleGrantedAuthority = List.of(new SimpleGrantedAuthority(customer.getRole()));

        // Build and return Spring Security User object
        return User.builder()
                .username(customer.getEmail())
                .password(customer.getPwd())
                .authorities(simpleGrantedAuthority)
                .build();
    }
}
