package com.learn.booking.bookingsystem.service.impl;

import com.learn.booking.bookingsystem.controller.model.order.request.CreateOrderRequest;
import com.learn.booking.bookingsystem.controller.model.order.request.UpdateOrderRequest;
import com.learn.booking.bookingsystem.controller.model.order.response.OrderResponse;
import com.learn.booking.bookingsystem.db.model.Order;
import com.learn.booking.bookingsystem.db.repository.OrderRepository;
import com.learn.booking.bookingsystem.exception.NotFoundException;
import com.learn.booking.bookingsystem.service.OrderService;
import com.learn.booking.bookingsystem.service.PatchHandler;
import com.learn.booking.bookingsystem.service.mapper.OrderMapper;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final PatchHandler patchHandler;

    @Override
    public OrderResponse createOrder(CreateOrderRequest order) {
        //TODO how to avoid multiple insert for orders_tickets? How to do it in batch?
        //TODO clarify if really SEQUENCE needed for batch save
        return orderMapper.orderToOrderResponse(orderRepository
            .save(orderMapper.createOrderRequestToOrder(order)));
    }

    @Override
    public OrderResponse updateOrder(UpdateOrderRequest orderRequest, UUID orderUuid) {
        Order orderApplyTo = orderRepository.getByUuid(orderUuid)
            .orElseThrow(() -> new NotFoundException("Order is not found"));
        return orderMapper.orderToOrderResponse(patchHandler.apply(orderApplyTo, orderRequest));
    }

    @Override
    public void deleteOrder(UUID uuid) {
        orderRepository.deleteByUuid(uuid);
    }

    @Override
    public OrderResponse getOrder(UUID uuid) {
        return orderMapper.orderToOrderResponse(orderRepository.getByUuid(uuid)
            .orElseThrow(() -> new NotFoundException("Order is not found")));
    }

    @Override
    public List<OrderResponse> getOrders(List<UUID> uuids) {
        return orderRepository.findAllByUuid(uuids).stream()
            .map(orderMapper::orderToOrderResponse).collect(Collectors.toList());
    }
}
