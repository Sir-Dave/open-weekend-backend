package com.project.open_weekend.event;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {


    private final EventRepository eventRepository;

    @Override
    public List<Event> getAllEvents(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public List<Event> getAllByUser(long userId, int pageNo, int pageSize) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Event createEvent(Event event) {

        return eventRepository.save(event);
    }


    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void deleteEventById(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Optional<Event> findEventById(Long eventId) {
        return eventRepository.findById(eventId);
    }

}