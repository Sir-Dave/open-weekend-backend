package com.project.open_weekend.event;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface EventService  {

    List<EventResponse> getAllEvents(int pageNo, int pageSize);
    List<EventResponse> getAllEventsByStartTime(int pageNo, int pageSize);

    List<EventResponse> getEventsByLocation(int pageNo, int pageSize, String city);

    List<EventResponse> getAllByUser(long userId, int pageNo, int pageSize);

    EventResponse createEvent(
            String name, String description, String location,
            String startTime, String endTime, MultipartFile image,
            String type, Set<String> tags
    );
    EventResponse updateEvent(
            long eventId, String name,String description,
            String location, String startTime, String endTime,
            MultipartFile image, String type, Set<String> tags
    );
    void deleteEventById(long eventId);
    EventResponse findEventById(long eventId);
}
