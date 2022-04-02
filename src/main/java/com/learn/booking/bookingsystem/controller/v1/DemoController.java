package com.learn.booking.bookingsystem.controller.v1;

import com.learn.booking.bookingsystem.controller.model.order.response.OrderResponse;
import com.learn.booking.bookingsystem.service.DemoFacade;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/demo", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DemoController {

    private final DemoFacade demoFacade;

    @GetMapping("/users/{userUuid}")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<Void> checkIfThreadSafe(@PathVariable UUID userUuid){
        demoFacade.checkIfThreadSafe(userUuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
