package com.project.open_weekend.event;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventServiceImpl eventService;

    public EventController (EventServiceImpl eventService){this.eventService = eventService;}

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Event> createEvent(@RequestBody EventRequest eventRequest) {
        Event createdEvent = eventService.createEvent(eventRequest);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }
}
