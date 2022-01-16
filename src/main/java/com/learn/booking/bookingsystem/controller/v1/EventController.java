package com.learn.booking.bookingsystem.controller.v1;

import com.learn.booking.bookingsystem.controller.model.event.request.CreateEventRequest;
import com.learn.booking.bookingsystem.controller.model.event.request.UpdateEventRequest;
import com.learn.booking.bookingsystem.controller.model.event.response.EventResponse;
import com.learn.booking.bookingsystem.controller.model.order.response.OrderResponse;
import com.learn.booking.bookingsystem.service.EventService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/events/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/{eventUuid}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable UUID eventUuid){
        return new ResponseEntity<>(eventService.getEvent(eventUuid), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<EventResponse> createEvent(@RequestBody CreateEventRequest eventRequest){
        return new ResponseEntity<>(eventService.createEvent(eventRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/{eventUuid}")
    public ResponseEntity<EventResponse> updateEvent(@RequestBody UpdateEventRequest eventRequest, @PathVariable UUID eventUuid){
        return new ResponseEntity<>(eventService.updateEvent(eventRequest, eventUuid), HttpStatus.OK);
    }

    @DeleteMapping("/{eventUuid}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID eventUuid){
        eventService.deleteEvent(eventUuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
