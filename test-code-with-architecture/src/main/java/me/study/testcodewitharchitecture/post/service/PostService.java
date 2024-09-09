package me.study.testcodewitharchitecture.post.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import me.study.testcodewitharchitecture.common.domain.exception.ResourceNotFoundException;
import me.study.testcodewitharchitecture.common.service.port.ClockHolder;
import me.study.testcodewitharchitecture.post.domain.Post;
import me.study.testcodewitharchitecture.post.domain.PostCreate;
import me.study.testcodewitharchitecture.post.domain.PostUpdate;
import me.study.testcodewitharchitecture.post.service.port.PostRepository;
import me.study.testcodewitharchitecture.user.domain.User;
import me.study.testcodewitharchitecture.user.service.port.UserRepository;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ClockHolder clockHolder;

    public Post getById(long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", id));
    }

    public Post create(PostCreate postCreate) {
        User writer = userRepository.getById(postCreate.getWriterId());
        Post post = Post.from(writer, postCreate, clockHolder);
        return postRepository.save(post);
    }

    public Post update(long id, PostUpdate postUpdate) {
        Post post = getById(id);
        post = post.update(postUpdate, clockHolder);
        return postRepository.save(post);
    }
}
