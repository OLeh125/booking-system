package com.learn.task.management.task2.service;

import com.learn.task.management.task2.model.Friendship;
import com.learn.task.management.task2.model.Message;
import com.learn.task.management.task2.model.Movie;
import com.learn.task.management.task2.model.MovieWatch;
import com.learn.task.management.task2.model.User;
import com.learn.task.management.task2.repository.SocialNetworkRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SocialNetworkServiceImpl implements SocialNetworkService {

    private final SocialNetworkRepository socialNetworkRepository;
    private final int MIN_LIMIT = 97; // letter 'a'
    private final int MAX_LIMIT = 122; // letter 'z'
    private final int STRING_LENGTH = 10;
    private final int LIMIT = 10;
    private final Random random = new Random();

    @Override
    public Double averageNumberOfMessagesByDay(LocalDate day) {
        return socialNetworkRepository.averageNumberOfMessagesByDay(day);
    }

    @Override
    public Long maxNumberOfNewFriendships(LocalDateTime from, LocalDateTime to) {
        return socialNetworkRepository.maxNumberOfNewFriendships(from,  to);
    }

    @Override
    public Long minNumberOfWatchedMoviesByUsersMore100Friends() {
        return socialNetworkRepository.minNumberOfWatchedMoviesByUsersMore100Friends();
    }

    @Override
    public void fillTestData() {
        socialNetworkRepository.addUsers(addRandomUsers());
        socialNetworkRepository.addMovies(addRandomMovies());
        socialNetworkRepository.addMovieWatches(addMovieWatches());
        socialNetworkRepository.addFriendships(addFriendships());
        socialNetworkRepository.addMessages(addMessages());
    }

    private List<MovieWatch> addMovieWatches() {
        List<MovieWatch> movieWatches = new ArrayList<>(LIMIT);
        List<UUID> usersUuids = socialNetworkRepository.getUsers().stream().map(User::getUuid)
            .collect(Collectors.toList());
        List<UUID> moviesUuids = socialNetworkRepository.getMovies().stream().map(Movie::getUuid)
            .collect(Collectors.toList());
        for (int i = 0; i < LIMIT; i++) {
            movieWatches.add(buildRandomCreateMovieWatchRequest(moviesUuids, usersUuids));
        }
        return movieWatches;
    }

    private List<Friendship> addFriendships() {
        List<Friendship> friendships = new ArrayList<>(LIMIT);
        List<UUID> usersUuids = socialNetworkRepository.getUsers().stream().map(User::getUuid)
            .collect(Collectors.toList());
        for (int i = 0; i < LIMIT; i++) {
            friendships.add(buildRandomCreateFriendshipRequest(usersUuids));
        }
        return friendships;
    }

    private List<Message> addMessages() {
        List<Message> messages = new ArrayList<>(LIMIT);
        List<UUID> usersUuids = socialNetworkRepository.getUsers().stream().map(User::getUuid)
            .collect(Collectors.toList());
        for (int i = 0; i < LIMIT; i++) {
            messages.add(buildRandomCreateMessageRequest(usersUuids));
        }
        return messages;
    }

    private Message buildRandomCreateMessageRequest(List<UUID> usersUuids) {
        UUID firstMember = usersUuids.get(random.nextInt(usersUuids.size() - 1));
        return Message.builder()
            .uuid(UUID.randomUUID())
            .sender(firstMember)
            .receiver(usersUuids.stream().filter(u -> !u.equals(firstMember))
                .collect(Collectors.toList()).get(random.nextInt(usersUuids.size() - 1)))
            .createdDate(LocalDateTime.now())
            .content(generateRandomString())
            .build();
    }

    private Friendship buildRandomCreateFriendshipRequest(List<UUID> usersUuids) {
        UUID firstMember = usersUuids.get(random.nextInt(usersUuids.size() - 1));
        return Friendship.builder()
            .uuid(UUID.randomUUID())
            .firstMember(firstMember)
            .secondMember(usersUuids.stream().filter(u -> !u.equals(firstMember))
                .collect(Collectors.toList()).get(random.nextInt(usersUuids.size() - 1)))
            .startDate(LocalDateTime.now())
            .build();
    }

    private MovieWatch buildRandomCreateMovieWatchRequest(List<UUID> movieUuids, List<UUID> usersUuids) {
        return MovieWatch.builder()
            .uuid(UUID.randomUUID())
            .movieId(movieUuids.get(random.nextInt(movieUuids.size() - 1)))
            .viewerId(usersUuids.get(random.nextInt(usersUuids.size() - 1)))
            .build();
    }

    private List<User> addRandomUsers() {
        List<User> users = new ArrayList<>(LIMIT);
        for (int i = 0; i < LIMIT; i++) {
            users.add(buildRandomCreateUserRequest());
        }
        return users;
    }

    private User buildRandomCreateUserRequest() {
        return User.builder()
            .uuid(UUID.randomUUID())
            .email(generateRandomString())
            .name(generateRandomString())
            .surname(generateRandomString())
            .password(generateRandomString())
            .build();
    }

    private List<Movie> addRandomMovies() {
        List<Movie> movies = new ArrayList<>(LIMIT);
        for (int i = 0; i < LIMIT; i++) {
            movies.add(buildRandomCreateMovieRequest());
        }
        return movies;
    }

    private Movie buildRandomCreateMovieRequest() {
        return Movie.builder()
            .uuid(UUID.randomUUID())
            .name(generateRandomString())
            .content(generateRandomString())
            .build();
    }

    private String generateRandomString() {
        return random.ints(MIN_LIMIT, MAX_LIMIT + 1)
            .limit(STRING_LENGTH)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }
}
