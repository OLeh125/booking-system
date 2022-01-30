package com.learn.booking.bookingsystem.security;

import com.learn.booking.bookingsystem.db.model.SecurityUser;
import com.learn.booking.bookingsystem.db.repository.SecurityUserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Qualifier("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SecurityUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUser securityUser = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(securityUser.getUsername(), securityUser.getPassword(), List.of(new SimpleGrantedAuthority("read"), new SimpleGrantedAuthority("write")));
    }
}
