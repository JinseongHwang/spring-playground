package me.study.testcodewitharchitecture.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import me.study.testcodewitharchitecture.post.domain.PostCreateDto;
import me.study.testcodewitharchitecture.post.domain.PostUpdateDto;
import me.study.testcodewitharchitecture.post.repository.PostEntity;
import me.study.testcodewitharchitecture.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

@SpringBootTest
@TestPropertySource("classpath:test-application.yml")
@SqlGroup({
    @Sql(value = "/sql/post-service-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
    @Sql(value = "/sql/delete-all-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void getById는_존재하는_게시물을_내려준다() {
        // given
        // when
        PostEntity result = postService.getById(1);

        // then
        assertThat(result.getContent()).isEqualTo("helloworld");
        assertThat(result.getWriter().getEmail()).isEqualTo("kok202@naver.com");
    }

    @Test
    void postCreateDto_를_이용하여_게시물을_생성할_수_있다() {
        // given
        PostCreateDto postCreateDto = PostCreateDto.builder()
            .writerId(1)
            .content("foobar")
            .build();

        // when
        // then
        assertThrows(
                DataIntegrityViolationException.class,
                () -> postService.create(postCreateDto)
        );
    }

    @Test
    void postUpdateDto_를_이용하여_게시물을_수정할_수_있다() {
        // given
        PostUpdateDto postUpdateDto = PostUpdateDto.builder()
            .content("hello world :)")
            .build();

        // when
        postService.update(1, postUpdateDto);

        // then
        PostEntity postEntity= postService.getById(1);
        assertThat(postEntity.getContent()).isEqualTo("hello world :)");
        assertThat(postEntity.getModifiedAt()).isGreaterThan(0);
    }

}
