package com.learn.booking.bookingsystem.db.repository.mongo;

import com.learn.booking.bookingsystem.db.mongo.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoCurrencyRepository extends MongoRepository<Currency, String> {

}
