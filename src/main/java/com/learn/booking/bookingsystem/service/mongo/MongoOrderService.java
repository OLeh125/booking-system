package com.learn.booking.bookingsystem.service.mongo;

import com.learn.booking.bookingsystem.controller.model.order.response.OrderResponse;
import com.learn.booking.bookingsystem.db.mongo.Order;
import java.util.List;
import java.util.UUID;

public interface MongoOrderService {

    OrderResponse create(Order order);

    List<OrderResponse> getAll();

    List<OrderResponse> createAll(List<Order> orders);

    OrderResponse get(UUID orderUuid);

}
