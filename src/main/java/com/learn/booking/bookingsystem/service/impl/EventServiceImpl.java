package com.learn.booking.bookingsystem.service.impl;

import com.learn.booking.bookingsystem.controller.model.event.request.CreateEventRequest;
import com.learn.booking.bookingsystem.controller.model.event.request.UpdateEventRequest;
import com.learn.booking.bookingsystem.controller.model.event.response.EventResponse;
import com.learn.booking.bookingsystem.db.model.Event;
import com.learn.booking.bookingsystem.db.repository.EventRepository;
import com.learn.booking.bookingsystem.exception.NotFoundException;
import com.learn.booking.bookingsystem.service.EventService;
import com.learn.booking.bookingsystem.service.PatchHandler;
import com.learn.booking.bookingsystem.service.mapper.EventMapper;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final PatchHandler patchHandler;

    @Override
    public EventResponse createEvent(CreateEventRequest event) {
        return eventMapper.eventToEventResponse(eventRepository.save(eventMapper.CreateEventRequestToEvent(event)));
    }

    @Override
    public EventResponse updateEvent(UpdateEventRequest eventRequest, UUID userUuid) {
        Event eventToApply = eventRepository.getByUuid(userUuid)
            .orElseThrow(()-> new NotFoundException("Event is not found"));
        return eventMapper.eventToEventResponse(patchHandler.apply(eventToApply, eventRequest));
    }

    @Override
    public void deleteEvent(UUID uuid) {
        eventRepository.deleteByUuid(uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public EventResponse getEvent(UUID uuid) {
        return eventMapper.eventToEventResponse(eventRepository.getByUuid(uuid)
            .orElseThrow(()-> new NotFoundException("Event is not found")));
    }

    @Override
    public List<EventResponse> getAll() {
        return eventRepository.findAll().stream().map(eventMapper::eventToEventResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getEvents(List<UUID> uuid) {
        return eventRepository.findAllByUuid(uuid).stream()
            .map(eventMapper::eventToEventResponse).collect(Collectors.toList());
    }

    @Override
    public void bookTickets(Integer number, UUID eventUuid) {
        Integer ticketsLeft = eventRepository.bookTickets(number, eventUuid);
        if (ticketsLeft < 0){
            int left = number + ticketsLeft;
            throw new IllegalArgumentException("There are not so much tickets available. Only " + left + " left");
        }
    }
}
