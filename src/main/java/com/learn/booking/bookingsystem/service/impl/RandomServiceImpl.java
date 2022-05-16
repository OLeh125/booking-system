package com.learn.booking.bookingsystem.service.impl;

import com.learn.booking.bookingsystem.controller.model.users.request.CreateUserRequest;
import com.learn.booking.bookingsystem.enums.UserStatus;
import com.learn.booking.bookingsystem.service.RandomService;
import com.learn.booking.bookingsystem.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RandomServiceImpl implements RandomService {

    private final UserService userService;
    private final int MIN_LIMIT = 97; // letter 'a'
    private final int MAX_LIMIT = 122; // letter 'z'
    private final int STRING_LENGTH = 10;
    private final int USERS_LIMIT = 50_000;
    private final Random random = new Random();

    @Override
    public void addRandomUsers() {
        List<CreateUserRequest> users = new ArrayList<>(1000);
        for (int i = 0; i < USERS_LIMIT; i++) {
            users.add(buildRandomCreateUserRequest());
        }
        userService.createUsers(users);
    }

    private CreateUserRequest buildRandomCreateUserRequest() {
        return CreateUserRequest.builder()
            .uuid(UUID.randomUUID())
            .email(generateRandomString())
            .name(generateRandomString())
            .surname(generateRandomString())
            .status(UserStatus.ACTIVE)
            .password(generateRandomString())
            .build();
    }

    private String generateRandomString() {
        return random.ints(MIN_LIMIT, MAX_LIMIT + 1)
            .limit(STRING_LENGTH)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }
}
