package com.spring.security.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 500)
    private String pwd;

    @Column(nullable = false, length = 50)
    private String role;

    @Column(nullable = false)
    private Boolean enabled = true;

}