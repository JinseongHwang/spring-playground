package me.study.testcodewitharchitecture.post.controller.response;

import lombok.Builder;
import lombok.Getter;
import me.study.testcodewitharchitecture.post.domain.Post;
import me.study.testcodewitharchitecture.user.controller.response.UserResponse;

@Getter
@Builder
public class PostResponse {

    private final Long id;
    private final String content;
    private final Long createdAt;
    private final Long modifiedAt;
    private final UserResponse writer;

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .writer(UserResponse.from(post.getWriter()))
                .build();
    }
}
