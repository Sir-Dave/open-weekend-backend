package com.project.open_weekend.event;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @Autowired
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String location,
            @RequestParam String startTime,
            @RequestParam String endTime,
            @RequestParam MultipartFile image,
            @RequestParam String type,
            @RequestParam Set<String> tags
    ) {
        var response = eventService.createEvent(
                name, description, location, startTime,
                endTime, image, type, tags
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> findAll(
            @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String city
    ){
        var response = city == null ?
                eventService.getAllEventsOrderByStartTime(pageNum,pageSize) :
                eventService.getEventsByLocation(pageNum, pageSize, city);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponse> findEventById(
            @PathVariable long eventId
    ) {
        var response = eventService.findEventById(eventId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponse> updateEvent(
            @PathVariable long eventId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) MultipartFile image,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Set<String> tags
    ) {
        var response = eventService.updateEvent(
                eventId, name, description,
                location, startTime, endTime,
                image, type, tags
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
