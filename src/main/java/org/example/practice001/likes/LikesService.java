package org.example.practice001.likes;

import lombok.RequiredArgsConstructor;
import org.example.practice001.feed.model.Feed;
import org.example.practice001.likes.model.Likes;
import org.example.practice001.user.model.AuthUserDetails;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likesRepository;

    public void like(AuthUserDetails user, Long idx) {
        Likes likes = Likes.builder()
                .user(user.toEntity())
                .feed(Feed.builder().idx(idx).build())
                .build();

        likesRepository.save(likes);
    }
}
