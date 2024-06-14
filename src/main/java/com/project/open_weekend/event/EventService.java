package com.project.open_weekend.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventService  {

    List<Event> getAllEvents(int pageNo, int pageSize);

    List<Event> getAllByUser(long userId, int pageNo, int pageSize);

    Event findById(long eventId);

}
