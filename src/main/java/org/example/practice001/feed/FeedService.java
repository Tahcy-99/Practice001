package org.example.practice001.feed;

import lombok.RequiredArgsConstructor;

import org.example.practice001.feed.model.Feed;
import org.example.practice001.feed.model.FeedDto;

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
}
