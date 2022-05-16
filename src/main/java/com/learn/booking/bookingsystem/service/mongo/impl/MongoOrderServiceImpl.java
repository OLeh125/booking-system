package com.learn.booking.bookingsystem.service.mongo.impl;

import com.learn.booking.bookingsystem.controller.model.order.response.OrderResponse;
import com.learn.booking.bookingsystem.db.mongo.Order;
import com.learn.booking.bookingsystem.db.repository.mongo.MongoOrderRepository;
import com.learn.booking.bookingsystem.service.mapper.OrderMapper;
import com.learn.booking.bookingsystem.service.mongo.MongoOrderService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class MongoOrderServiceImpl implements MongoOrderService {

    private final MongoOrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse create(Order order) {
        return orderMapper.mongoOrderToOrderResponse(orderRepository.insert(order));
    }

    @Override
    public List<OrderResponse> getAll() {
        return null;
    }

    @Override
    public List<OrderResponse> createAll(List<Order> orders) {
        return orderRepository.insert(orders).stream().map(orderMapper::mongoOrderToOrderResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse get(UUID orderUuid) {
        return null;
    }
}
