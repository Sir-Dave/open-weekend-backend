package com.project.open_weekend.event;

import java.util.List;

public interface EventService {

    Event createEvent(EventRequest eventRequest);
    List<Event> findEventListByLocation(String location);
    Event updateEvent(Event event);
    void deleteEventById(Long eventId);
    Event findEventById(Long eventId);


}
