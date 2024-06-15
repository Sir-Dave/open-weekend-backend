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
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Event createEvent(EventRequest eventRequest) {
        Event event = Event.builder()
                .name(eventRequest.getName())
                .description(eventRequest.getDescription())
                .location(eventRequest.getLocation())
                .startTime(eventRequest.getStartTime())
                .endTime(eventRequest.getEndTime())
                .imageUrl(eventRequest.getImageUrl())
                .type(eventRequest.getType())
                .tags(eventRequest.getTags())
                .socialMediaLinks(eventRequest.getSocialMediaLinks())
                .isApproved(eventRequest.isApproved())
                .build();
        return eventRepository.save(event);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<Event> findEventListByLocation(String location) {
        return eventRepository.findByLocation(location);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Event findEventById(Long eventId) {
        return null;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteEventById(Long eventId) {
        eventRepository.deleteById(eventId);
    }

}
