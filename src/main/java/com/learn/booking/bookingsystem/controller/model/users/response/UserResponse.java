package com.learn.booking.bookingsystem.controller.model.users.response;

import com.learn.booking.bookingsystem.enums.UserStatus;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {

    private Integer id;
    private UUID uuid;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserStatus status;
    private Set<AccountResponse> accounts;


}
