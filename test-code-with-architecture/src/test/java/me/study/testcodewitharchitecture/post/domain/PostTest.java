package me.study.testcodewitharchitecture.post.domain;

import me.study.testcodewitharchitecture.common.service.port.ClockHolder;
import me.study.testcodewitharchitecture.mock.TestClockHolder;
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
        ClockHolder clockHolder = new TestClockHolder(1678530673958L);

        // when
        Post post = Post.from(writer, postCreate, clockHolder);

        // then
        assertThat(post.getContent()).isEqualTo("helloworld");
        assertThat(post.getWriter().getEmail()).isEqualTo("jinseong@example.com");
        assertThat(post.getWriter().getNickname()).isEqualTo("nickname");
        assertThat(post.getWriter().getAddress()).isEqualTo("address");
        assertThat(post.getWriter().getStatus()).isEqualTo(UserStatus.ACTIVE);
        assertThat(post.getWriter().getCertificationCode()).isEqualTo("code");
        assertThat(post.getCreatedAt()).isEqualTo(1678530673958L);
    }

    @Test
    public void PostUpdate로_게시글을_수정할_수_있다() {
        // given
        PostUpdate postUpdate = PostUpdate.builder()
                .content("foobar")
                .build();
        User writer = User.builder()
                .email("jinseong@example.com")
                .nickname("nickname")
                .address("address")
                .status(UserStatus.ACTIVE)
                .certificationCode("code")
                .build();
        Post post = Post.builder()
                .id(1L)
                .content("helloworld")
                .createdAt(1678530673958L)
                .modifiedAt(0L)
                .writer(writer)
                .build();
        ClockHolder clockHolder = new TestClockHolder(1678530673958L);

        // when
        post = post.update(postUpdate, clockHolder);

        // then
        assertThat(post.getContent()).isEqualTo("foobar");
        assertThat(post.getModifiedAt()).isEqualTo(1678530673958L);
    }
}
