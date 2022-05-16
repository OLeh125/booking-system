package com.learn.booking.bookingsystem.db.repository.mongo;

import com.learn.booking.bookingsystem.db.mongo.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoTicketRepository extends MongoRepository<Ticket, String> {

}
