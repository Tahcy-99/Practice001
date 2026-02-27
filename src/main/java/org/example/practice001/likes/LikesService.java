package org.example.practice001.likes;

import lombok.RequiredArgsConstructor;
import org.example.practice001.feed.model.Feed;
import org.example.practice001.likes.model.Likes;
import org.example.practice001.user.model.AuthUserDetails;
import org.example.practice001.user.model.User;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final LikesRepository likesRepository;

    public void like(AuthUserDetails user, Long idx) {
        User userEntity = user.toEntity();
        Feed feedEntity = Feed.builder().idx(idx).build();
        Optional<Likes> result = likesRepository.findByUserAndFeed(userEntity,feedEntity);

        if(result.isPresent()){
            likesRepository.delete(result.get());
        }
        else{
            Likes likes = Likes.builder()
                    .user(userEntity)
                    .feed(feedEntity)
                    .build();
            likesRepository.save(likes);
        }
    }
}
