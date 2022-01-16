package com.learn.booking.bookingsystem.service;


import com.learn.booking.bookingsystem.controller.model.order.request.CreateOrderRequest;
import com.learn.booking.bookingsystem.controller.model.order.request.UpdateOrderRequest;
import com.learn.booking.bookingsystem.controller.model.order.response.OrderResponse;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    
    OrderResponse createOrder(CreateOrderRequest order);

    OrderResponse updateOrder(UpdateOrderRequest order, UUID orderUuid);

    void deleteOrder(UUID uuid);

    OrderResponse getOrder(UUID uuid);

    List<OrderResponse> getOrders(List<UUID> uuid);

}
