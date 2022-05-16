package com.learn.booking.bookingsystem.service.mongo;

import com.learn.booking.bookingsystem.controller.model.users.response.AccountResponse;
import com.learn.booking.bookingsystem.db.mongo.Account;
import java.util.List;
import java.util.UUID;

public interface MongoAccountService {

    AccountResponse create(Account account);

    List<AccountResponse> getAll();

    List<AccountResponse> createAll(List<Account> accounts);

    AccountResponse get(UUID accountUuid);

}
