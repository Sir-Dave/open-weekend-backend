package com.project.open_weekend.event;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventServiceImpl eventService;

    public EventController (EventServiceImpl eventService){this.eventService = eventService;}

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Event> createEvent(@RequestBody EventRequest eventRequest) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String username = authentication.getName();
        Event createdEvent = eventService.createEvent(eventRequest, username);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @GetMapping
//    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<Event>> findAll(@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize){
        Page<Event> eventPage = eventService.getAllEventsByStartTime(pageNum,pageSize);
        return ResponseEntity.ok(eventPage.getContent());
    }

    @GetMapping("/city/{city}")
//    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<Event>> findAllByCity(@PathVariable String city, @RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        Page<Event> eventPage = eventService.getEventsByLocation(pageNum, pageSize, city);
        return ResponseEntity.ok(eventPage.getContent());
    }




}
