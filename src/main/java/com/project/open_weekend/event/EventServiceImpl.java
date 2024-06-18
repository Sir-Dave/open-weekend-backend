package com.project.open_weekend.event;

import com.project.open_weekend.exception.EntityNotFoundException;
import com.project.open_weekend.mapper.EventMapper;
import com.project.open_weekend.user.UserService;
import com.project.open_weekend.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private final EventRepository eventRepository;
    private final UserService userService;

    @Override
    public List<EventResponse> getAllEvents(int pageNo, int pageSize) {
        var pageable = PageRequest.of(pageNo, pageSize);
        Page<Event> pagedResult = eventRepository.getAllEvents(pageable);
        return mapToResponse(pagedResult);
    }

    @Override
    public List<EventResponse> getAllEventsByStartTime(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Event> pagedResult = eventRepository.findAllByOrderByStartTime(pageable);
        return mapToResponse(pagedResult);
    }

    @Override
    public List<EventResponse> getEventsByLocation(int pageNo, int pageSize, String city) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Event> pagedResult = eventRepository.findByCity(pageable, city);
        return mapToResponse(pagedResult);
    }

    @Override
    public List<EventResponse> getAllByUser(long userId, int pageNo, int pageSize) {
        var pageable = PageRequest.of(pageNo, pageSize);
        Page<Event> pagedResult = eventRepository.getAllEventsByUser(userId, pageable);
        return mapToResponse(pagedResult);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public EventResponse createEvent(EventRequest eventRequest) {
        var user = userService.findUserById(eventRequest.getCreatorId());
        var event = EventMapper.mapRequestToEvent(eventRequest);
        event.addToCreatorEvents(user);
        event.setImageUrl(uploadImage(eventRequest.getImage()));
        var savedEvent = eventRepository.save(event);
        return EventMapper.mapEventToEventResponse(savedEvent);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public EventResponse updateEvent(EventRequest eventRequest) {
        var event = eventRepository.findById(eventRequest.getEventId()).orElseThrow(
                () -> new EntityNotFoundException(String.format("No event with id %s was found", eventRequest.getEventId()))
        );

        if (eventRequest.getCreatorId() != event.getCreator().getId()){
            throw new AccessDeniedException("You do not have permission to edit this event");
        }

        if (!eventRequest.getName().isBlank())
            event.setName(eventRequest.getName());

        if (!eventRequest.getDescription().isBlank())
            event.setDescription(eventRequest.getDescription());

        if (!eventRequest.getLocation().isBlank())
            event.setLocation(eventRequest.getLocation());

        if (eventRequest.getStartTime() != null && eventRequest.getEndTime() != null){
            var startTime = Util.getLocalDateTime(eventRequest.getStartTime());
            var endTime = Util.getLocalDateTime(eventRequest.getEndTime());
            validateTimes(startTime, endTime);
        }

        if (eventRequest.getImage() != null)
            event.setImageUrl(uploadImage(eventRequest.getImage()));

        if (!eventRequest.getType().isBlank())
            event.setType(eventRequest.getType());

        if (!eventRequest.getTags().isEmpty())
            event.setTags(eventRequest.getTags());

        var updatedEvent = eventRepository.save(event);
        return EventMapper.mapEventToEventResponse(updatedEvent);
    }

    @Override
    public void deleteEventById(long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public EventResponse findEventById(long eventId) {
        var event = eventRepository.findById(eventId).orElseThrow(
                () -> new EntityNotFoundException(String.format("No event with id %s was found", eventId))
        );
        return EventMapper.mapEventToEventResponse(event);
    }

    private String uploadImage(MultipartFile file){
        if (file.isEmpty()){
            throw new IllegalStateException("File cannot be empty");
        }

        //TODO: Upload to a cloud storage and return link
        return "";
    }

    private void validateTimes(LocalDateTime startTime, LocalDateTime endTime){
        if (startTime.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Invalid start time");
        }

        if (endTime.isBefore(startTime)){
            throw new IllegalStateException("Invalid start and end time");
        }
    }

    private void validateSocialMediaLinks(Map<String, String> socialMediaLinks){
        //TODO: Validate that the keys in the map are social media
    }

    private List<EventResponse> mapToResponse(Page<Event> pagedResult){
        List<Event> events = pagedResult.hasContent() ? pagedResult.getContent() : new ArrayList<>();
        return events.stream().map(EventMapper::mapEventToEventResponse).collect(Collectors.toList());
    }
}