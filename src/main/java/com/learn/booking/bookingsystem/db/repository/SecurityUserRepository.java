package com.learn.booking.bookingsystem.db.repository;

import com.learn.booking.bookingsystem.db.model.SecurityUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SecurityUserRepository extends JpaRepository<SecurityUser, Long> {

    @Transactional(readOnly = true)
    Optional<SecurityUser> findByUsername(String username);
}
