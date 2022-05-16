package com.learn.booking.bookingsystem.service.mongo.impl;

import com.learn.booking.bookingsystem.controller.model.users.response.UserResponse;
import com.learn.booking.bookingsystem.db.mongo.User;
import com.learn.booking.bookingsystem.db.repository.mongo.MongoUserRepository;
import com.learn.booking.bookingsystem.service.mapper.UserMapper;
import com.learn.booking.bookingsystem.service.mongo.MongoUserService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MongoUserServiceImpl implements MongoUserService {

    private final UserMapper userMapper;
    private final MongoUserRepository mongoUserRepository;

    @Override
    public UserResponse create(User user) {
        return userMapper.mongoUserToUserResponse(mongoUserRepository.insert(user));
    }

    @Override
    public List<UserResponse> getAll() {
        return mongoUserRepository.findAll().stream().map(userMapper::mongoUserToUserResponse).collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> createAll(List<User> users) {
        return mongoUserRepository.insert(users).stream().map(userMapper::mongoUserToUserResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse get(UUID userUuid) {
        return null;
    }
}
