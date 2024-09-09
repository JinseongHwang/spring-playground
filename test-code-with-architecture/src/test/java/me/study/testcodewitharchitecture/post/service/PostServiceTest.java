package me.study.testcodewitharchitecture.post.service;

import me.study.testcodewitharchitecture.mock.FakePostRepository;
import me.study.testcodewitharchitecture.mock.FakeUserRepository;
import me.study.testcodewitharchitecture.mock.TestClockHolder;
import me.study.testcodewitharchitecture.post.domain.Post;
import me.study.testcodewitharchitecture.post.domain.PostCreate;
import me.study.testcodewitharchitecture.post.domain.PostUpdate;
import me.study.testcodewitharchitecture.user.domain.User;
import me.study.testcodewitharchitecture.user.domain.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PostServiceTest {

    private PostService postService;

    @BeforeEach
    void init() {
        FakeUserRepository userRepository = new FakeUserRepository();
        User user1 = User.builder()
                .id(1L)
                .email("kok202@naver.com")
                .nickname("kok202")
                .address("Seoul")
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
                .status(UserStatus.ACTIVE)
                .lastLoginAt(0L)
                .build();
        User user2 = User.builder()
                .id(2L)
                .email("kok303@naver.com")
                .nickname("kok303")
                .address("Seoul")
                .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
                .status(UserStatus.PENDING)
                .lastLoginAt(0L)
                .build();
        userRepository.save(user1);
        userRepository.save(user2);

        FakePostRepository postRepository = new FakePostRepository();
        Post post = Post.builder()
                .id(1L)
                .content("helloworld")
                .createdAt(1678530673958L)
                .modifiedAt(0L)
                .writer(user1)
                .build();
        postRepository.save(post);
        this.postService = PostService.builder()
                .postRepository(postRepository)
                .userRepository(userRepository)
                .clockHolder(new TestClockHolder(1678530673958L))
                .build();
    }

    @Test
    void getById는_존재하는_게시물을_내려준다() {
        // given
        // when
        Post result = postService.getById(1);

        // then
        assertThat(result.getContent()).isEqualTo("helloworld");
        assertThat(result.getWriter().getEmail()).isEqualTo("kok202@naver.com");
    }

    @Test
    void postCreateDto_를_이용하여_게시물을_생성할_수_있다() {
        // given
        PostCreate postCreate = PostCreate.builder()
                .writerId(1)
                .content("foobar")
                .build();

        // when
        Post result = postService.create(postCreate);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getContent()).isEqualTo("foobar");
        assertThat(result.getCreatedAt()).isEqualTo(1678530673958L);
    }

    @Test
    void postUpdateDto_를_이용하여_게시물을_수정할_수_있다() {
        // given
        PostUpdate postUpdate = PostUpdate.builder()
                .content("hello world :)")
                .build();

        // when
        postService.update(1, postUpdate);

        // then
        Post post1 = postService.getById(1);
        assertThat(post1.getContent()).isEqualTo("hello world :)");
        assertThat(post1.getModifiedAt()).isEqualTo(1678530673958L);
    }

}
