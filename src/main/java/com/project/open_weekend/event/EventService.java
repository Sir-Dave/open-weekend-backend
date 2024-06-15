package com.project.open_weekend.event;

import java.util.List;
import java.util.Optional;


public interface EventService  {

    List<Event> getAllEvents(int pageNo, int pageSize);

    List<Event> getAllByUser(long userId, int pageNo, int pageSize);

    Event createEvent(Event event);
    Event updateEvent(Event event);
    void deleteEventById(Long eventId);
    Optional<Event> findEventById(Long eventId);
}
