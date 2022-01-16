package com.learn.booking.bookingsystem.service.impl;


import com.learn.booking.bookingsystem.controller.model.users.request.CreateUserRequest;
import com.learn.booking.bookingsystem.controller.model.users.request.UpdateUserRequest;
import com.learn.booking.bookingsystem.controller.model.users.response.UserResponse;
import com.learn.booking.bookingsystem.db.model.User;
import com.learn.booking.bookingsystem.db.repository.UserRepository;
import com.learn.booking.bookingsystem.exception.NotFoundException;
import com.learn.booking.bookingsystem.service.PatchHandler;
import com.learn.booking.bookingsystem.service.UserService;
import com.learn.booking.bookingsystem.service.mapper.UserMapper;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PatchHandler patchHandler;

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
    public UserResponse getUser(UUID uuid) {
        return userMapper.userToUserResponse(userRepository.getByUuid(uuid)
            .orElseThrow(() -> new NotFoundException("User is not found")));
    }

    @Override
    public List<UserResponse> getUsers(List<UUID> uuids) {
        return userRepository.getAllByUuid(uuids).stream()
            .map(userMapper::userToUserResponse).collect(Collectors.toList());
    }
}
