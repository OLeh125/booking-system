package com.learn.booking.bookingsystem.db.repository.mongo;

import com.learn.booking.bookingsystem.db.mongo.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoAccountRepository extends MongoRepository<Account, String> {

}
