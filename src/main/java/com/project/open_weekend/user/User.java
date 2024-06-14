package com.project.open_weekend.user;


import com.project.open_weekend.event.Event;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "creator", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<Event> events = new HashSet<>();

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

    public void addEvent(Event event){
        events.add(event);
        event.setCreator(this);
    }

    public void removeEvent(Event event){
        events.remove(event);
        event.setCreator(null);
    }
}

