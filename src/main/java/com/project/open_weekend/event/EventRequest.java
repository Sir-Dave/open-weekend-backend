package com.project.open_weekend.event;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class EventRequest {
    private String name;
    private String description;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String imageUrl;
    private String type;
    private Set<String> tags;
    private boolean isApproved = false;
    private List<String> socialMediaLinks;
}
