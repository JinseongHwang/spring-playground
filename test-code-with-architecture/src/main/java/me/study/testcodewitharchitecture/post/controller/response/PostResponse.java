package me.study.testcodewitharchitecture.post.controller.response;

import lombok.Getter;
import lombok.Setter;
import me.study.testcodewitharchitecture.user.controller.response.UserResponse;

@Getter
@Setter
public class PostResponse {

    private Long id;
    private String content;
    private Long createdAt;
    private Long modifiedAt;
    private UserResponse writer;
}
