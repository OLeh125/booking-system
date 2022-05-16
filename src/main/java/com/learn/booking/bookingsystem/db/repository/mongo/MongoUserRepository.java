package com.learn.booking.bookingsystem.db.repository.mongo;

import com.learn.booking.bookingsystem.db.mongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserRepository extends MongoRepository<User, String> {

}
