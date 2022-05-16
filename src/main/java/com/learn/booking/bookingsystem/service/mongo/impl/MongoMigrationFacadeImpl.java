package com.learn.booking.bookingsystem.service.mongo.impl;

import com.learn.booking.bookingsystem.db.mongo.Account;
import com.learn.booking.bookingsystem.db.mongo.Event;
import com.learn.booking.bookingsystem.db.mongo.Order;
import com.learn.booking.bookingsystem.db.mongo.Ticket;
import com.learn.booking.bookingsystem.db.mongo.User;
import com.learn.booking.bookingsystem.service.AccountService;
import com.learn.booking.bookingsystem.service.EventService;
import com.learn.booking.bookingsystem.service.OrderService;
import com.learn.booking.bookingsystem.service.TicketService;
import com.learn.booking.bookingsystem.service.UserService;
import com.learn.booking.bookingsystem.service.mapper.AccountMapper;
import com.learn.booking.bookingsystem.service.mapper.EventMapper;
import com.learn.booking.bookingsystem.service.mapper.OrderMapper;
import com.learn.booking.bookingsystem.service.mapper.TicketMapper;
import com.learn.booking.bookingsystem.service.mapper.UserMapper;
import com.learn.booking.bookingsystem.service.mongo.MongoAccountService;
import com.learn.booking.bookingsystem.service.mongo.MongoEventService;
import com.learn.booking.bookingsystem.service.mongo.MongoMigrationFacade;
import com.learn.booking.bookingsystem.service.mongo.MongoOrderService;
import com.learn.booking.bookingsystem.service.mongo.MongoTicketService;
import com.learn.booking.bookingsystem.service.mongo.MongoUserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MongoMigrationFacadeImpl implements MongoMigrationFacade {

    private final UserService userService;
    private final MongoUserService mongoUserService;
    private final UserMapper userMapper;
    private final EventService eventService;
    private final MongoEventService mongoEventService;
    private final EventMapper eventMapper;
    private final AccountService accountService;
    private final MongoAccountService mongoAccountService;
    private final AccountMapper accountMapper;
    private final TicketService ticketService;
    private final MongoTicketService mongoTicketService;
    private final TicketMapper ticketMapper;
    private final OrderService orderService;
    private final MongoOrderService mongoOrderService;
    private final OrderMapper orderMapper;

    @Override
    public void migrateAllUsers() {
        List<User> users = userService.getAll().stream().map(userMapper::userResponseToMongoUser)
            .collect(Collectors.toList());
        mongoUserService.createAll(users);
    }

    @Override
    public void migrateAllEvents() {
        List<Event> events = eventService.getAll().stream().map(eventMapper::eventResponseToMongoEvent)
            .collect(Collectors.toList());
        mongoEventService.createAll(events);
    }

    @Override
    public void migrateAllAccounts() {
        List<Account> accounts = accountService.getAll().stream().map(accountMapper::accountResponseToMongoAccount)
            .collect(Collectors.toList());
        mongoAccountService.createAll(accounts);
    }

    @Override
    public void migrateAllTickets() {
        List<Ticket> tickets = ticketService.getAll().stream().map(ticketMapper::ticketResponseToMongoTicket)
            .collect(Collectors.toList());
        mongoTicketService.createAll(tickets);
    }

    @Override
    public void migrateAllOrders() {
        List<Order> orders = orderService.getAll().stream().map(orderMapper::orderResponseToMongoOrder)
            .collect(Collectors.toList());
        mongoOrderService.createAll(orders);
    }

    @Override
    public void migrateAllData() {
        migrateAllUsers();
        migrateAllEvents();
        migrateAllAccounts();
        migrateAllTickets();
        migrateAllOrders();
    }
}
