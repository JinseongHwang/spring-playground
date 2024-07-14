package me.study.testcodewitharchitecture.post.service.port;

import me.study.testcodewitharchitecture.post.domain.Post;

import java.util.Optional;

public interface PostRepository {
    Optional<Post> findById(long id);

    Post save(Post post);
}
