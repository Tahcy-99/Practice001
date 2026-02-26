package org.example.practice001.feed;

import org.example.practice001.feed.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed,Long> {
    @Query("SELECT f FROM Feed f JOIN FETCH f.user")
    List<Feed> findAllWithUser();
}
