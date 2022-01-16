package com.learn.booking.bookingsystem.service.mapper;

import com.learn.booking.bookingsystem.controller.model.event.request.CreateEventRequest;
import com.learn.booking.bookingsystem.controller.model.event.request.UpdateEventRequest;
import com.learn.booking.bookingsystem.controller.model.event.response.EventResponse;
import com.learn.booking.bookingsystem.db.model.Event;
import org.springframework.stereotype.Service;

@Service
public class EventMapper {

    public EventResponse eventToEventResponse(Event event){
        return EventResponse.builder()
            .id(event.getId())
            .uuid(event.getUuid())
            .place(event.getPlace())
            .date(event.getDate())
            .description(event.getDescription())
            .status(event.getStatus())
            .ticketsNumber(event.getTicketsNumber())
            .build();
    }

    public Event CreateEventRequestToEvent(CreateEventRequest event){
        return Event.builder()
            .id(event.getId())
            .uuid(event.getUuid())
            .place(event.getPlace())
            .date(event.getDate())
            .description(event.getDescription())
            .status(event.getStatus())
            .ticketsNumber(event.getTicketsNumber())
            .build();
    }

}
