package com.learn.task.management.task2.repository;

import com.learn.task.management.task2.model.Friendship;
import com.learn.task.management.task2.model.Message;
import com.learn.task.management.task2.model.Movie;
import com.learn.task.management.task2.model.MovieWatch;
import com.learn.task.management.task2.model.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SocialNetworkRepository {

    Double averageNumberOfMessagesByDay(LocalDate day);

    Long maxNumberOfNewFriendships(LocalDateTime from, LocalDateTime to);

    Long minNumberOfWatchedMoviesByUsersMore100Friends();

    Integer addUsers(List<User> users);

    List<User> getUsers();

    Integer addMovies(List<Movie> movies);

    List<Movie> getMovies();

    Integer addMovieWatches(List<MovieWatch> movieWatches);

    List<MovieWatch> getMovieWatches();

    Integer addFriendships(List<Friendship> friendships);

    List<Friendship> getFriendships();

    Integer addMessages(List<Message> messages);

    List<Message> getMessages();

}
