package com.learn.booking.bookingsystem.controller.v1;

import com.learn.booking.bookingsystem.aop.Timed;
import com.learn.booking.bookingsystem.controller.model.users.request.CreateUserRequest;
import com.learn.booking.bookingsystem.controller.model.users.request.UpdateUserRequest;
import com.learn.booking.bookingsystem.controller.model.users.response.UserResponse;
import com.learn.booking.bookingsystem.service.UserService;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userUuid}")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID userUuid) {
        return new ResponseEntity<>(userService.getUser(userUuid), HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<List<UserResponse>> getUsers(@RequestParam List<UUID> uuids) {
        return new ResponseEntity<>(userService.getUsersWithAccounts(uuids), HttpStatus.OK);
    }

    @GetMapping("/postgres")
    @PreAuthorize("hasAuthority('read')")
    @Timed(msg = "get all users postgres")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/mongo")
    @PreAuthorize("hasAuthority('read')")
    @Timed(msg = "get all users mongo")
    public ResponseEntity<List<UserResponse>> getAllMongoUsers() {
        return new ResponseEntity<>(userService.getAllMongoUsers(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest userRequest) {
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/{userUuid}")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserRequest userRequest,
        @PathVariable UUID userUuid) {
        return new ResponseEntity<>(userService.updateUser(userRequest, userUuid), HttpStatus.OK);
    }

    @DeleteMapping("/{userUuid}")
    @PreAuthorize("hasAuthority('write')")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userUuid) {
        userService.deleteUser(userUuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
