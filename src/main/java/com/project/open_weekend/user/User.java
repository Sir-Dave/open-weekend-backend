package com.project.open_weekend.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
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
}

