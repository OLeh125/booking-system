package com.learn.booking.bookingsystem.service.mapper;

import com.learn.booking.bookingsystem.controller.model.users.request.CreateUserRequest;
import com.learn.booking.bookingsystem.controller.model.users.response.UserResponse;
import com.learn.booking.bookingsystem.db.mongo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final AccountMapper accountMapper;

    public UserResponse userToUserResponse(com.learn.booking.bookingsystem.db.model.User user){
        return UserResponse.builder()
            .id(user.getId().toString())
            .uuid(user.getUuid())
            .name(user.getName())
            .surname(user.getSurname())
            .email(user.getEmail())
            .password(user.getPassword())
            .status(user.getStatus())
            .accounts(accountMapper.accountsToAccountsResponses(user.getAccounts()))
            .build();
    }

    public com.learn.booking.bookingsystem.db.model.User createUserRequestToUser(CreateUserRequest userRequest){
        return com.learn.booking.bookingsystem.db.model.User.builder()
            .id(userRequest.getId())
            .uuid(userRequest.getUuid())
            .name(userRequest.getName())
            .surname(userRequest.getSurname())
            .email(userRequest.getEmail())
            .password(userRequest.getPassword())
            .status(userRequest.getStatus())
            .build();
    }

    public User createUserRequestToMongoUser(CreateUserRequest userRequest){
        return User.builder()
            .uuid(userRequest.getUuid())
            .name(userRequest.getName())
            .surname(userRequest.getSurname())
            .email(userRequest.getEmail())
            .password(userRequest.getPassword())
            .status(userRequest.getStatus())
            .build();
    }

    public UserResponse mongoUserToUserResponse(User user) {
        return UserResponse.builder()
            .id(user.getId())
            .uuid(user.getUuid())
            .name(user.getName())
            .surname(user.getSurname())
            .email(user.getEmail())
            .password(user.getPassword())
            .status(user.getStatus())
            .build();
    }

    public User userResponseToMongoUser(UserResponse user) {
        return User.builder()
            .uuid(user.getUuid())
            .name(user.getName())
            .surname(user.getSurname())
            .email(user.getEmail())
            .password(user.getPassword())
            .status(user.getStatus())
            .build();
    }
}
