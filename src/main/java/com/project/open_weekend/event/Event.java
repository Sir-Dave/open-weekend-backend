package com.project.open_weekend.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    private String type;

    @ElementCollection(targetClass = String.class)
    private Set<String> tags;

    private boolean isApproved;

    @ElementCollection(targetClass = String.class)
    private List<String> socialMediaLinks;
}

