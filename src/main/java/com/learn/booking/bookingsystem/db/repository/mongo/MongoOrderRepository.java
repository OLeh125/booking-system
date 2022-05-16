package com.learn.booking.bookingsystem.db.repository.mongo;

import com.learn.booking.bookingsystem.db.mongo.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoOrderRepository extends MongoRepository<Order, String> {

}
