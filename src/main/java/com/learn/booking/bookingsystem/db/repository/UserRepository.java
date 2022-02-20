package com.learn.booking.bookingsystem.db.repository;

import com.learn.booking.bookingsystem.db.model.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
//TODO ask about transactional in JpaRepositories https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#transactional-query-methods,
//TODO also just mention about @Modifying flushAutomatically and clearAutomatically. And discuss entityManager.close()
//TODO https://stackoverflow.com/questions/3274958/question-about-hibernate-session-flush
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email = :email")
    @Transactional(readOnly = true)
    Optional<User> getByEmail(String email);

    @Query("select u from User u where u.uuid = :uuid")
    @Transactional(readOnly = true)
    Optional<User> getByUuid(UUID uuid);

    @Query("select u from User u where u.uuid in (:uuids)")
    @Transactional(readOnly = true)
    List<User> getAllByUuid(List<UUID> uuids);

    @Modifying
    @Query("delete from User u where u.uuid = :uuid")
    void deleteByUuid(UUID uuid);

}