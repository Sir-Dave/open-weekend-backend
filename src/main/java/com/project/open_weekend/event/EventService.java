package com.project.open_weekend.event;

import java.util.List;


public interface EventService  {

    List<EventResponse> getAllEvents(int pageNo, int pageSize);

    List<EventResponse> getAllByUser(long userId, int pageNo, int pageSize);

    EventResponse createEvent(EventRequest eventRequest);
    EventResponse updateEvent(EventRequest eventRequest);
    void deleteEventById(long eventId);
    EventResponse findEventById(long eventId);
}
