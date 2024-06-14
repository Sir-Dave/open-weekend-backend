package com.project.open_weekend.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Event findById(long eventId) {
        return null;
    }
}
