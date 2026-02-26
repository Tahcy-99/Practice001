package org.example.practice001.feed.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.practice001.likes.model.Likes;
import org.example.practice001.user.model.User;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String title;
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_idx")
    private User user;

//    @OneToMany(mappedBy = "feed")
//    List<Reply> replyList;
    private int replyCount;

    @OneToMany(mappedBy = "feed", fetch = FetchType.LAZY)
    List<Likes> likesList;
//    private int likesCount;
}
