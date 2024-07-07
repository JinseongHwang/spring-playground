package me.study.testcodewitharchitecture.post.service;

import me.study.testcodewitharchitecture.common.domain.exception.ResourceNotFoundException;
import me.study.testcodewitharchitecture.post.domain.PostCreateDto;
import me.study.testcodewitharchitecture.post.domain.PostUpdateDto;
import me.study.testcodewitharchitecture.post.repository.PostEntity;
import me.study.testcodewitharchitecture.post.repository.PostRepository;
import me.study.testcodewitharchitecture.user.repository.UserEntity;
import java.time.Clock;
import lombok.RequiredArgsConstructor;
import me.study.testcodewitharchitecture.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostEntity getById(long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", id));
    }

    public PostEntity create(PostCreateDto postCreateDto) {
        UserEntity userEntity = userService.getById(postCreateDto.getWriterId());
        PostEntity postEntity = new PostEntity();
        postEntity.setWriter(userEntity);
        postEntity.setContent(postCreateDto.getContent());
        postEntity.setCreatedAt(Clock.systemUTC().millis());
        return postRepository.save(postEntity);
    }

    public PostEntity update(long id, PostUpdateDto postUpdateDto) {
        PostEntity postEntity = getById(id);
        postEntity.setContent(postUpdateDto.getContent());
        postEntity.setModifiedAt(Clock.systemUTC().millis());
        return postRepository.save(postEntity);
    }
}
