package com.learn.booking.bookingsystem.controller.v1;

import com.learn.booking.bookingsystem.controller.model.users.request.CreateUserRequest;
import com.learn.booking.bookingsystem.controller.model.users.request.UpdateUserRequest;
import com.learn.booking.bookingsystem.controller.model.users.response.UserResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/users/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final com.learn.booking.bookingsystem.service.UserService UserService;

    @GetMapping("/{userUuid}")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID userUuid){
        return new ResponseEntity<>(UserService.getUser(userUuid), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest userRequest){
        return new ResponseEntity<>(UserService.createUser(userRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/{userUuid}")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserRequest userRequest, @PathVariable UUID userUuid){
        return new ResponseEntity<>(UserService.updateUser(userRequest, userUuid), HttpStatus.OK);
    }

    @DeleteMapping("/{userUuid}")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userUuid){
        UserService.deleteUser(userUuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
