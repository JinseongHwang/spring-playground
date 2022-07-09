package me.study.resttemplate.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.ThreadLocalRandom;
import me.study.resttemplate.domain.user.User;
import me.study.resttemplate.error.CustomException;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    RestTemplate basicRestTemplate;

    @Autowired
    RestTemplate customRestTemplate;

    private static final int TEST_REPS = 100;

    private static final String URI_FORMAT = "http://localhost:%d/users/%d";
    private static final int MIN_ID = 1; // inclusive
    private static final int MAX_ID = 6; // exclusive

    // --- Using Basic RestTemplate

    @RepeatedTest(TEST_REPS)
    void getUser_success_using_basicRestTemplate() {
        // Given
        int randomId = ThreadLocalRandom.current().nextInt(MIN_ID, MAX_ID);

        // When & Then
        assertDoesNotThrow(
            () -> basicRestTemplate.getForEntity(
                String.format(URI_FORMAT, port, randomId),
                User.class
            )
        );
    }

    @RepeatedTest(TEST_REPS)
    void getUser_failure_using_basicRestTemplate() {
        // Given
        int randomId = ThreadLocalRandom.current().nextInt(MAX_ID, Integer.MAX_VALUE);

        // When & Then
        assertThrows(
            HttpServerErrorException.class,
            () -> basicRestTemplate.getForEntity(
                String.format(URI_FORMAT, port, randomId),
                User.class
            )
        );
    }

    // --- Using Custom RestTemplate

    @RepeatedTest(TEST_REPS)
    void getUser_success_using_customRestTemplate() {
        // Given
        int randomId = ThreadLocalRandom.current().nextInt(MIN_ID, MAX_ID);

        // When & Then
        assertDoesNotThrow(
            () -> customRestTemplate.getForEntity(
                String.format(URI_FORMAT, port, randomId),
                User.class
            )
        );
    }

    @RepeatedTest(TEST_REPS)
    void getUser_failure_using_customRestTemplate() {
        // Given
        int randomId = ThreadLocalRandom.current().nextInt(MAX_ID, Integer.MAX_VALUE);

        // When & Then
        assertThrows(CustomException.class,
                     () -> customRestTemplate.getForEntity(
                         String.format(URI_FORMAT, port, randomId),
                         User.class
                     )
        );
    }
}