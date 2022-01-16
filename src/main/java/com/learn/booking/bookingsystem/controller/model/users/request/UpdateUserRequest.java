package com.learn.booking.bookingsystem.controller.model.users.request;

import com.learn.booking.bookingsystem.enums.UserStatus;
import com.learn.booking.bookingsystem.service.annotations.Immutable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateUserRequest {

    @Immutable
    private Long id;
    @Immutable
    private UUID uuid;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserStatus status;

}
