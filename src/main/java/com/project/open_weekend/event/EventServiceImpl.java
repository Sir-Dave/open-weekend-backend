package com.project.open_weekend.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Override
    @PreAuthorize("")
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> findEventListByLocation(String location) {
        return eventRepository.findByLocation(location);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event findEventById(Long eventId) {
        return null;
    }

    @Override
    public void deleteEventById(Long eventId) {
        eventRepository.deleteById(eventId);
    }

}
