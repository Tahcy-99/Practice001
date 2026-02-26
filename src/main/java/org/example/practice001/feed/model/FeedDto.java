package org.example.practice001.feed.model;

import lombok.Builder;
import lombok.Getter;
import org.example.practice001.likes.model.Likes;
import org.example.practice001.user.model.User;

public class FeedDto {

    @Builder
    @Getter
    public static class ListRes{
    private Long idx;
    private String title;
    private String writer;
    private int replyCount;
    private int likesCount;

    public static ListRes toDto(Feed entity){
        return ListRes.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .writer(entity.getUser().getName())
                .replyCount(entity.getReplyCount())
                .likesCount(entity.likesList.size())
                .build();
        }
    }

    @Getter
    public static class FeedReg {
        private String title;
        private String contents;
        private String writer;

        public Feed toEntity(){
            return Feed.builder()
                    .title(this.title)
                    .contents(this.contents)
                    .user(User.builder().name(this.writer).build())
                    .build();
        }
    }
}
