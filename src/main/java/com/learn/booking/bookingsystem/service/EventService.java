package com.learn.booking.bookingsystem.service;

import com.learn.booking.bookingsystem.controller.model.event.request.CreateEventRequest;
import com.learn.booking.bookingsystem.controller.model.event.request.UpdateEventRequest;
import com.learn.booking.bookingsystem.controller.model.event.response.EventResponse;
import java.util.List;
import java.util.UUID;

public interface EventService {
    
    EventResponse createEvent(CreateEventRequest event);

    EventResponse updateEvent(UpdateEventRequest event, UUID eventUuid);

    void deleteEvent(UUID uuid);

    EventResponse getEvent(UUID uuid);

    List<EventResponse> getAll();

    List<EventResponse> getEvents(List<UUID> uuid);

    void bookTickets(Integer number, UUID eventUuid);
}
