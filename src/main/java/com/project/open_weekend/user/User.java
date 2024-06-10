package com.project.open_weekend.user;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private long id;
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String password;

    private LocalDateTime dateJoined;

    private String role;

    private String[] authorities;

    private boolean isActive;

    private boolean isNotLocked;

    public User(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String password,
            LocalDateTime dateJoined,
            String role,
            String[] authorities,
            boolean isActive,
            boolean isNotLocked
    ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.dateJoined = dateJoined;
        this.role = role;
        this.authorities = authorities;
        this.isActive = isActive;
        this.isNotLocked = isNotLocked;
    }
}

