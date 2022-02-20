package com.learn.booking.bookingsystem.service.impl;


import com.learn.booking.bookingsystem.controller.model.users.request.CreateUserRequest;
import com.learn.booking.bookingsystem.controller.model.users.request.UpdateUserRequest;
import com.learn.booking.bookingsystem.controller.model.users.response.UserResponse;
import com.learn.booking.bookingsystem.db.model.Account;
import com.learn.booking.bookingsystem.db.model.User;
import com.learn.booking.bookingsystem.db.repository.AccountRepository;
import com.learn.booking.bookingsystem.db.repository.UserRepository;
import com.learn.booking.bookingsystem.exception.NotFoundException;
import com.learn.booking.bookingsystem.service.PatchHandler;
import com.learn.booking.bookingsystem.service.UserService;
import com.learn.booking.bookingsystem.service.mapper.UserMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PatchHandler patchHandler;
    private final AccountRepository accountRepository;

    @Override
    public UserResponse createUser(CreateUserRequest user) {
        return userMapper.userToUserResponse(userRepository
            .save(userMapper.createUserRequestToUser(user)));
    }

    @Override
    public UserResponse updateUser(UpdateUserRequest userRequest, UUID userUuid) {
        User userApplyTo = userRepository.getByUuid(userUuid)
            .orElseThrow(() -> new NotFoundException("User is not found"));
        return userMapper.userToUserResponse(patchHandler.apply(userApplyTo, userRequest));
    }

    @Override
    public void deleteUser(UUID uuid) {
        userRepository.deleteByUuid(uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUser(UUID uuid) {
        return userMapper.userToUserResponse(userRepository.getByUuid(uuid)
            .orElseThrow(() -> new NotFoundException("User is not found")));
    }

    @Override
    //Advanced Multithreading TASK 4
    @Transactional(readOnly = true)
    public List<UserResponse> getUsersWithAccounts(List<UUID> uuids) {
        List<User> users = userRepository.getAllByUuid(uuids);
        List<CompletableFuture<User>> usersFuture = new ArrayList<>();
        for (User user : users) {
            CompletableFuture<User> userFuture = CompletableFuture
                .supplyAsync(() -> setUserAccounts(user));
            usersFuture.add(userFuture);
        }
        return usersFuture.stream().map(CompletableFuture::join).map(userMapper::userToUserResponse).collect(Collectors.toList());
    }

    private User setUserAccounts(User user){
        user.setAccounts(accountRepository.getAllByOwnerId(user.getId()));
        return user;
    }
}
