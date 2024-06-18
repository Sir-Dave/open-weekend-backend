package com.project.open_weekend.event;

import com.project.open_weekend.user.User;
import com.project.open_weekend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {


    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public Page<Event> getAllEventsByStartTime(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return eventRepository.findAllByOrderByStartTime(pageable);
    }

    @Override
    public Page<Event> getEventsByLocation(int pageNo, int pageSize, String city) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return eventRepository.findByCity(pageable, city);
    }

    @Override
    public List<Event> getAllByUser(long userId, int pageNo, int pageSize) {
        return null;
    }

    @Override
    public Event createEvent(EventRequest eventRequest, String username) {
        Optional<User> currentUser =  userRepository.findUserByEmail(username);
        Event event = new Event();
        event.setName(eventRequest.getName());
        event.setDescription(eventRequest.getDescription());
        event.setLocation(eventRequest.getLocation());
        event.setStartTime(eventRequest.getStartTime());
        event.setEndTime(eventRequest.getEndTime());
        event.setImageUrl(eventRequest.getImageUrl());
        event.setType(eventRequest.getType());
        event.setTags(eventRequest.getTags());
        event.setApproved(eventRequest.isApproved());
        event.setSocialMediaLinks(eventRequest.getSocialMediaLinks());
        currentUser.ifPresent(event::setCreator);
        return eventRepository.save(event);
    }

}