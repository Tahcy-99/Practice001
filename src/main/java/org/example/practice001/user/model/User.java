package org.example.practice001.user.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.practice001.feed.model.Feed;
import org.example.practice001.likes.model.Likes;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String email;
    private String name;
    @Setter
    private String password;
    @Setter
    private boolean enable;
    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Feed> feedList;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    List<Reply> replyList;
//
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Likes> likesList;
}