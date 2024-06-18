package com.project.open_weekend.event;


import lombok.Data;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class EventRequest {
    private long eventId;
    private String name;
    private String description;
    private String location;
    private String startTime;
    private String endTime;
    private MultipartFile image;
    private long creatorId;
    private String type;
    private Set<String> tags;
}
