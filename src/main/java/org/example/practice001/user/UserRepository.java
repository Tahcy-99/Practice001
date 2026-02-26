package org.example.practice001.user;

import org.example.practice001.feed.model.Feed;
import org.example.practice001.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
