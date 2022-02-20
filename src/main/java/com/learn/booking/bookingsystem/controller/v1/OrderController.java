package com.learn.booking.bookingsystem.controller.v1;

import com.learn.booking.bookingsystem.controller.model.order.request.CreateOrderRequest;
import com.learn.booking.bookingsystem.controller.model.order.request.UpdateOrderRequest;
import com.learn.booking.bookingsystem.controller.model.order.response.OrderResponse;
import com.learn.booking.bookingsystem.service.OrderService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping(value = "/api/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderUuid}")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable UUID orderUuid){
        return new ResponseEntity<>(orderService.getOrder(orderUuid), HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest orderRequest){
        return new ResponseEntity<>(orderService.createOrder(orderRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/{orderUuid}")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<OrderResponse> updateOrder(@RequestBody UpdateOrderRequest orderRequest, @PathVariable UUID orderUuid){
        return new ResponseEntity<>(orderService.updateOrder(orderRequest, orderUuid), HttpStatus.OK);
    }

    @DeleteMapping("/{orderUuid}")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID orderUuid){
        orderService.deleteOrder(orderUuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}