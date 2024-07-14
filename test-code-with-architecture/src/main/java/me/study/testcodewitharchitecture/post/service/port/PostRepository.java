package me.study.testcodewitharchitecture.post.service.port;

import me.study.testcodewitharchitecture.post.infrastructure.PostEntity;

import java.util.Optional;

public interface PostRepository {
    Optional<PostEntity> findById(long id);

    PostEntity save(PostEntity postEntity);
}
