package me.study.testcodewitharchitecture.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.study.testcodewitharchitecture.post.domain.PostCreate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SqlGroup({
        @Sql(value = "/sql/post-create-controller-test-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
public class PostCreateControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @Test
    void 사용자는_게시물을_작성할_수_있다() throws Exception {
        // given
        PostCreate postCreate = PostCreate.builder()
                .writerId(1)
                .content("helloworld")
                .build();

        // when
        // then
        mockMvc.perform(
                        post("/api/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(postCreate)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.content").value("helloworld"))
                .andExpect(jsonPath("$.writer.id").isNumber())
                .andExpect(jsonPath("$.writer.email").value("kok202@naver.com"))
                .andExpect(jsonPath("$.writer.nickname").value("kok202"));
    }
}
