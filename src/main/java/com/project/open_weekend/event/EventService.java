package com.project.open_weekend.event;

import org.springframework.data.domain.Page;

import java.util.List;


public interface EventService  {

    List<EventResponse> getAllEvents(int pageNo, int pageSize);
    List<EventResponse> getAllEventsByStartTime(int pageNo, int pageSize);

    List<EventResponse> getEventsByLocation(int pageNo, int pageSize, String city);

    List<EventResponse> getAllByUser(long userId, int pageNo, int pageSize);

    EventResponse createEvent(EventRequest eventRequest);
    EventResponse updateEvent(EventRequest eventRequest);
    void deleteEventById(long eventId);
    EventResponse findEventById(long eventId);
    //Event createEvent(EventRequest eventRequest, String username);
}
