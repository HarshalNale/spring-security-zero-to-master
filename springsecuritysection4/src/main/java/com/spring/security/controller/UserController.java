package com.spring.security.controller;

import com.spring.security.dto.CustomerDto;
import com.spring.security.entity.Customer;
import com.spring.security.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CustomerDto customerDto) {
        String encodedPwd = passwordEncoder.encode(customerDto.getPwd());
        Customer customer = Customer.builder().
                email(customerDto.getEmail()).
                pwd(encodedPwd).
                role(customerDto.getRole()).
                build();
        Customer savedCustomer = customerRepository.save(customer);

        if (savedCustomer != null && savedCustomer.getId() > 0) {
            return ResponseEntity.ok("Customer registered successfully!");
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}
