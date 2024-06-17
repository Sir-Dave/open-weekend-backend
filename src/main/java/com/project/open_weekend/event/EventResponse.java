package com.project.open_weekend.event;

import lombok.Builder;
import lombok.Getter;
import java.util.Map;
import java.util.Set;

@Getter
@Builder
public class EventResponse {
    private long id;

    private String name;

    private String description;

    private String location;

    private String startTime;

    private String endTime;

    private String imageUrl;

    private String type;

    private String creator;

    private Set<String> tags;

    private boolean isApproved;
    private Map<String, String> socialMediaLinks;
}
