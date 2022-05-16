package com.learn.task.management.task2.repository;

import com.learn.task.management.task2.model.Friendship;
import com.learn.task.management.task2.model.Message;
import com.learn.task.management.task2.model.Movie;
import com.learn.task.management.task2.model.MovieWatch;
import com.learn.task.management.task2.model.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
//TODO check if works
public class SocialNetworkRepositoryImpl implements SocialNetworkRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Double averageNumberOfMessagesByDay(LocalDate day) {
        GroupOperation messagesByReceiver = Aggregation.group("receiver")
            .count().as("messagesByReceiver");
        GroupOperation averageNumberOfMessages = Aggregation.group("_id.messages")
            .avg("messagesByReceiver").as("averageNumberOfMessages");
        MatchOperation before = Aggregation.match(Criteria.where("createdDate").lt(day.atStartOfDay()));
        MatchOperation after = Aggregation.match(Criteria.where("createdDate").gt(day.minusDays(1).atStartOfDay()));
        Aggregation aggregation = Aggregation.newAggregation(before, after, messagesByReceiver, averageNumberOfMessages);
        AggregationResults<LinkedHashMap> result = mongoTemplate.aggregate(aggregation, Message.class, LinkedHashMap.class);
        Object res = result.getUniqueMappedResult().get("averageNumberOfMessages");
        return ((Double)res);
    }

    @Override
    public Long maxNumberOfNewFriendships(LocalDateTime from, LocalDateTime to) {
        GroupOperation numberOfNewFriends = Aggregation.group("firstMember")
            .count().as("numberOfNewFriends");
        GroupOperation maxNewFriends = Aggregation.group("_id.friendship")
            .max("numberOfNewFriends").as("maxNewFriends");
        MatchOperation fromOp = Aggregation.match(Criteria.where("startDate").lt(from));
        MatchOperation toOp = Aggregation.match(Criteria.where("startDate").gt(to));
        Aggregation aggregation = Aggregation.newAggregation(fromOp, toOp, numberOfNewFriends, maxNewFriends);
        AggregationResults<LinkedHashMap> result = mongoTemplate.aggregate(aggregation, Friendship.class, LinkedHashMap.class);
        Object res = result.getUniqueMappedResult().get("maxNewFriends");
        return ((Integer)res).longValue();
    }

    @Override
    public Long minNumberOfWatchedMoviesByUsersMore100Friends() {
        GroupOperation friendsOp = Aggregation.group("firstMember").count().as("friends");
        MatchOperation friendsGtOp = Aggregation.match(Criteria.where("friends").gt(100));
        Aggregation aggregation = Aggregation.newAggregation(friendsOp, friendsGtOp);
        AggregationResults<Friendship> friendshipResults = mongoTemplate.aggregate(aggregation, Friendship.class,
            Friendship.class);
        List<Friendship> friendships = friendshipResults.getMappedResults();
        List<UUID> usersWithMore100FriendsUuids = friendships.stream().map(Friendship::getFirstMember).collect(Collectors.toList());

        MatchOperation usersWithMore100Friends = Aggregation.match(Criteria.where("viewerId").in(usersWithMore100FriendsUuids));
        GroupOperation numberOfWatchedMovies = Aggregation.group("viewerId")
            .count().as("numberOfWatchedMovies");
        GroupOperation minNumberOfWatchedMovies = Aggregation.group("_id.movieWatch")
            .min("numberOfWatchedMovies").as("minNumberOfWatchedMovies");
        Aggregation aggregation2 = Aggregation.newAggregation(usersWithMore100Friends, numberOfWatchedMovies, minNumberOfWatchedMovies);
        AggregationResults<LinkedHashMap> result = mongoTemplate.aggregate(aggregation2, MovieWatch.class, LinkedHashMap.class);
        Object res = result.getUniqueMappedResult().get("minNumberOfWatchedMovies");
        return ((Integer)res).longValue();
    }

    @Override
    public Integer addUsers(List<User> users) {
        return mongoTemplate.insertAll(users).size();
    }

    @Override
    public List<User> getUsers() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public Integer addMovies(List<Movie> movies) {
        return mongoTemplate.insertAll(movies).size();
    }

    @Override
    public List<Movie> getMovies() {
        return mongoTemplate.findAll(Movie.class);
    }

    @Override
    public Integer addMovieWatches(List<MovieWatch> movieWatches) {
        return mongoTemplate.insertAll(movieWatches).size();
    }

    @Override
    public List<MovieWatch> getMovieWatches() {
        return mongoTemplate.findAll(MovieWatch.class);
    }

    @Override
    public Integer addFriendships(List<Friendship> friendships) {
        return mongoTemplate.insertAll(friendships).size();
    }

    @Override
    public List<Friendship> getFriendships() {
        return mongoTemplate.findAll(Friendship.class);
    }

    @Override
    public Integer addMessages(List<Message> messages) {
        return mongoTemplate.insertAll(messages).size();
    }

    @Override
    public List<Message> getMessages() {
        return mongoTemplate.findAll(Message.class);
    }
}
