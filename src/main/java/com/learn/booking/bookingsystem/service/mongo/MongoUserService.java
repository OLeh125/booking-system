package com.learn.booking.bookingsystem.service.mongo;

import com.learn.booking.bookingsystem.controller.model.users.response.UserResponse;
import com.learn.booking.bookingsystem.db.mongo.User;
import java.util.List;
import java.util.UUID;

public interface MongoUserService {

    UserResponse create(User user);

    List<UserResponse> getAll();

    List<UserResponse> createAll(List<User> users);

    UserResponse get(UUID userUuid);


}
