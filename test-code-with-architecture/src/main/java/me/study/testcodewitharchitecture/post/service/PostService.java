package me.study.testcodewitharchitecture.post.service;

import me.study.testcodewitharchitecture.common.domain.exception.ResourceNotFoundException;
import me.study.testcodewitharchitecture.post.domain.PostCreate;
import me.study.testcodewitharchitecture.post.domain.PostUpdate;
import me.study.testcodewitharchitecture.post.infrastructure.PostEntity;
import me.study.testcodewitharchitecture.post.infrastructure.PostJpaRepository;
import me.study.testcodewitharchitecture.user.infrastructure.UserEntity;
import java.time.Clock;
import lombok.RequiredArgsConstructor;
import me.study.testcodewitharchitecture.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;
    private final UserService userService;

    public PostEntity getById(long id) {
        return postJpaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", id));
    }

    public PostEntity create(PostCreate postCreate) {
        UserEntity userEntity = userService.getById(postCreate.getWriterId());
        PostEntity postEntity = new PostEntity();
        postEntity.setWriter(userEntity);
        postEntity.setContent(postCreate.getContent());
        postEntity.setCreatedAt(Clock.systemUTC().millis());
        return postJpaRepository.save(postEntity);
    }

    public PostEntity update(long id, PostUpdate postUpdate) {
        PostEntity postEntity = getById(id);
        postEntity.setContent(postUpdate.getContent());
        postEntity.setModifiedAt(Clock.systemUTC().millis());
        return postJpaRepository.save(postEntity);
    }
}
