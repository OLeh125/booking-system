package com.learn.booking.bookingsystem.service.mongo.impl;

import com.learn.booking.bookingsystem.controller.model.event.response.EventResponse;
import com.learn.booking.bookingsystem.db.mongo.Event;
import com.learn.booking.bookingsystem.db.repository.mongo.MongoEventRepository;
import com.learn.booking.bookingsystem.service.mapper.EventMapper;
import com.learn.booking.bookingsystem.service.mongo.MongoEventService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MongoEventServiceImpl implements MongoEventService {

    private final MongoEventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public EventResponse create(Event event) {
        return eventMapper.mongoEventToEventResponse(eventRepository.insert(event));
    }

    @Override
    public List<EventResponse> getAll() {
        return null;
    }

    @Override
    public List<EventResponse> createAll(List<Event> events) {
        return eventRepository.insert(events).stream().map(eventMapper::mongoEventToEventResponse).collect(Collectors.toList());
    }

    @Override
    public EventResponse get(UUID eventUuid) {
        return null;
    }
}
