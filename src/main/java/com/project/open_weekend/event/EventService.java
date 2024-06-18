package com.project.open_weekend.event;

import org.springframework.data.domain.Page;

import java.util.List;


public interface EventService  {

    Page<Event> getAllEventsByStartTime(int pageNo, int pageSize);

    Page<Event> getEventsByLocation(int pageNo, int pageSize, String city);

    List<Event> getAllByUser(long userId, int pageNo, int pageSize);

    Event createEvent(EventRequest eventRequest, String username);
}
