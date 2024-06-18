package com.project.open_weekend.event;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.open_weekend.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "events")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_sequence")
    @SequenceGenerator(name = "event_sequence", sequenceName = "event_sequence", allocationSize = 1)
    @Column(nullable = false, updatable = false)
    private long id;

    private String name;

    private String description;

    private String location;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String imageUrl;

    private String city;

    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User creator;

    @ElementCollection(targetClass = String.class)
    private Set<String> tags;

    private boolean isApproved;

    public void addToCreatorEvents(User user){
        user.addEvent(this);
    }
}

