package com.learn.task.management.task2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MinNewFriendsResponse {
    private String id;
    private Object minNewFriends;
}
