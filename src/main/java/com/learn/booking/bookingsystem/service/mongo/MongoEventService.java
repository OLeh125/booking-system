package com.learn.booking.bookingsystem.service.mongo;

import com.learn.booking.bookingsystem.controller.model.event.response.EventResponse;
import com.learn.booking.bookingsystem.db.mongo.Event;
import com.learn.booking.bookingsystem.db.mongo.User;
import java.util.List;
import java.util.UUID;

public interface MongoEventService {

    EventResponse create(Event event);

    List<EventResponse> getAll();

    List<EventResponse> createAll(List<Event> events);

    EventResponse get(UUID eventUuid);

}
