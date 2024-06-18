package com.project.open_weekend.event;

import com.project.open_weekend.auth.AuthenticatedUserService;
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
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private final EventRepository eventRepository;
    private final UserService userService;
    private final AuthenticatedUserService authUserService;

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
    public EventResponse createEvent(
            String name, String description, String location,
            String startTime, String endTime, MultipartFile image,
            String type, Set<String> tags
    ) {
        var user = authUserService.getAuthenticatedUser();

        var localDateStartTime = Util.getLocalDateTime(startTime);
        var localDateEndTime = Util.getLocalDateTime(endTime);
        validateTimes(localDateStartTime, localDateEndTime);

        var event = Event.builder()
                .name(name)
                .description(description)
                .location(location)
                .startTime(localDateStartTime)
                .endTime(localDateEndTime)
                .type(type)
                .tags(tags)
                .isApproved(false)
                .build();

        event.addToCreatorEvents(user);
        event.setImageUrl(uploadImage(image));
        var savedEvent = eventRepository.save(event);
        return EventMapper.mapEventToEventResponse(savedEvent);
    }

    @Override
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    //@PreAuthorize("@authenticatedUserService.hasId(#eventRequest.creatorId)")
    public EventResponse updateEvent(
            long eventId, String name, String description,
            String location, String startTime, String endTime,
            MultipartFile image, String type, Set<String> tags
    ) {
        var event = eventRepository.findById(eventId).orElseThrow(
                () -> new EntityNotFoundException(String.format("No event with id %s was found", eventId))
        );

        var user = authUserService.getAuthenticatedUser();

        if (user.getId() != event.getCreator().getId()){
            throw new AccessDeniedException("You do not have permission to edit this event");
        }

        if (!name.isBlank())
            event.setName(name);

        if (!description.isBlank())
            event.setDescription(description);

        if (!location.isBlank())
            event.setLocation(location);

        if (startTime != null && endTime != null){
            var localDateStartTime = Util.getLocalDateTime(startTime);
            var localDateEndTime = Util.getLocalDateTime(endTime);
            validateTimes(localDateStartTime, localDateEndTime);
        }

        if (image != null)
            event.setImageUrl(uploadImage(image));

        if (!type.isBlank())
            event.setType(type);

        if (!tags.isEmpty())
            event.setTags(tags);

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