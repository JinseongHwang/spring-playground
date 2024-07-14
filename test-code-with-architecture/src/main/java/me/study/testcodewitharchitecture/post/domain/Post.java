package me.study.testcodewitharchitecture.post.domain;

import lombok.Builder;
import lombok.Getter;
import me.study.testcodewitharchitecture.user.domain.User;

import java.time.Clock;

@Getter
public class Post {

    private final Long id;
    private final String content;
    private final Long createdAt;
    private final Long modifiedAt;
    private final User writer;

    @Builder
    public Post(Long id, String content, Long createdAt, Long modifiedAt, User writer) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.writer = writer;
    }

    public static Post from(User writer, PostCreate postCreate) {
        return Post.builder()
                .content(postCreate.getContent())
                .createdAt(Clock.systemUTC().millis())
                .writer(writer)
                .build();
    }

    public Post update(PostUpdate postUpdate) {
        return Post.builder()
                .id(id)
                .content(postUpdate.getContent())
                .createdAt(createdAt)
                .modifiedAt(Clock.systemUTC().millis())
                .writer(writer)
                .build();
    }
}
