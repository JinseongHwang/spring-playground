package me.study.testcodewitharchitecture.post.domain;

import me.study.testcodewitharchitecture.user.domain.User;
import me.study.testcodewitharchitecture.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {

    @Test
    public void PostCreate로_게시글을_만들_수_있다() {
        // given
        PostCreate postCreate = PostCreate.builder()
                .writerId(1)
                .content("helloworld")
                .build();
        User writer = User.builder()
                .email("jinseong@example.com")
                .nickname("nickname")
                .address("address")
                .status(UserStatus.ACTIVE)
                .certificationCode("code")
                .build();

        // when
        Post post = Post.from(writer, postCreate);

        // then
        assertThat(post.getContent()).isEqualTo("helloworld");
        assertThat(post.getWriter().getEmail()).isEqualTo("jinseong@example.com");
        assertThat(post.getWriter().getNickname()).isEqualTo("nickname");
        assertThat(post.getWriter().getAddress()).isEqualTo("address");
        assertThat(post.getWriter().getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(post.getWriter().getCertificationCode()).isEqualTo("code");
    }
}
