package com.learn.booking.bookingsystem.db.repository.mongo;

import com.learn.booking.bookingsystem.db.mongo.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoEventRepository extends MongoRepository<Event, String> {

}
