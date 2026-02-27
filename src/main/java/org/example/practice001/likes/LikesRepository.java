package org.example.practice001.likes;

import org.example.practice001.feed.model.Feed;
import org.example.practice001.likes.model.Likes;
import org.example.practice001.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByUserAndFeed(User userEntity, Feed feedEntity);
}
