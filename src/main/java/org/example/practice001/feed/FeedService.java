package org.example.practice001.feed;

import lombok.RequiredArgsConstructor;

import org.example.practice001.feed.model.Feed;
import org.example.practice001.feed.model.FeedDto;

import org.example.practice001.user.model.AuthUserDetails;
import org.example.practice001.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;

    @Transactional(readOnly = true)
    public List<FeedDto.ListRes> list(){
        List<Feed> feedList = feedRepository.findAllWithUser();
        return feedList.stream().map(FeedDto.ListRes::toDto).toList();
    }

    public void reg(FeedDto.FeedReg dto, AuthUserDetails user) {
        Feed feed = Feed.builder()
                .title(dto.getTitle())
                .contents(dto.getContents())
                .user(User.builder().idx(user.getIdx()).build())
                .likesList(new ArrayList<>())
                .replyCount(0)
                .build();

        feedRepository.save(feed);
    }
}
