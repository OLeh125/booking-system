package com.learn.booking.bookingsystem.service;

import com.learn.booking.bookingsystem.controller.model.users.response.AccountResponse;
import java.util.List;

public interface AccountService {

    List<AccountResponse> getAll();

}
