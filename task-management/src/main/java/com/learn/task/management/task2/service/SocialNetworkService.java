package com.learn.task.management.task2.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface SocialNetworkService {

    Double averageNumberOfMessagesByDay(LocalDate day);

    Long maxNumberOfNewFriendships(LocalDateTime from, LocalDateTime to);

    Long minNumberOfWatchedMoviesByUsersMore100Friends();

    void fillTestData();

}
