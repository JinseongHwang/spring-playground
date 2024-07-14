package me.study.testcodewitharchitecture.post.infrastructure;

import lombok.RequiredArgsConstructor;
import me.study.testcodewitharchitecture.post.domain.Post;
import me.study.testcodewitharchitecture.post.service.port.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository postJpaRepository;

    @Override
    public Optional<Post> findById(long id) {
        return postJpaRepository.findById(id).map(PostEntity::toModel);
    }

    @Override
    public Post save(Post post) {
        return postJpaRepository.save(PostEntity.fromModel(post)).toModel();
    }
}
