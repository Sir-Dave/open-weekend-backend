package com.project.open_weekend.mapper;

import com.project.open_weekend.event.Event;
import com.project.open_weekend.event.EventResponse;
import com.project.open_weekend.util.Util;

public class EventMapper {
    public static EventResponse mapEventToEventResponse(Event event){
        var user = event.getCreator();
        var creatorName = String.format("%s %s", user.getFirstName(), user.getLastName());

        return EventResponse.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .location(event.getLocation())
                .startTime(Util.getFormattedDate(event.getStartTime()))
                .endTime(Util.getFormattedDate(event.getEndTime()))
                .imageUrl(event.getImageUrl())
                .type(event.getType())
                .creator(creatorName)
                .tags(event.getTags())
                .isApproved(event.isApproved())
                .build();
    }
}
