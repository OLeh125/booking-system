package com.learn.booking.bookingsystem.service;


import com.learn.booking.bookingsystem.controller.model.users.request.CreateUserRequest;
import com.learn.booking.bookingsystem.controller.model.users.request.UpdateUserRequest;
import com.learn.booking.bookingsystem.controller.model.users.response.UserResponse;
import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse createUser(CreateUserRequest user);

    void createUsers(List<CreateUserRequest> users);

    UserResponse updateUser(UpdateUserRequest user, UUID userUuid);

    void deleteUser(UUID uuid);

    UserResponse getUser(UUID uuid);

    List<UserResponse> getUsersWithAccounts(List<UUID> uuid);

    List<UserResponse> getAll();

    List<UserResponse> getAllMongoUsers();
}
