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

    private static final String URI_FORMAT = "http://localhost:%d/users/%d";
    private static final int SUCCESS_MIN_ID = 1;
    private static final int SUCCESS_MAX_ID = 5;

    // --- Using Basic RestTemplate

    @RepeatedTest(10)
    void getUser_success_using_basicRestTemplate() {
        // Given
        int randomId = ThreadLocalRandom.current().nextInt(SUCCESS_MIN_ID, SUCCESS_MAX_ID);

        // When & Then
        assertDoesNotThrow(() -> basicRestTemplate.getForEntity(String.format(URI_FORMAT, port, randomId), User.class));
    }

    @RepeatedTest(10)
    void getUser_failure_using_basicRestTemplate() {
        // Given
        int randomId = ThreadLocalRandom.current().nextInt(SUCCESS_MAX_ID + 1, Integer.MAX_VALUE);

        // When & Then
        assertThrows(HttpServerErrorException.class,
                     () -> basicRestTemplate.getForEntity(String.format(URI_FORMAT, port, randomId), User.class));
    }

    // --- Using Custom RestTemplate

    @RepeatedTest(10)
    void getUser_success_using_customRestTemplate() {
        // Given
        int randomId = ThreadLocalRandom.current().nextInt(SUCCESS_MIN_ID, SUCCESS_MAX_ID);

        // When & Then
        assertDoesNotThrow(() -> customRestTemplate.getForEntity(String.format(URI_FORMAT, port, randomId), User.class));
    }

    @RepeatedTest(10)
    void getUser_failure_using_customRestTemplate() {
        // Given
        int randomId = ThreadLocalRandom.current().nextInt(SUCCESS_MAX_ID + 1, Integer.MAX_VALUE);

        // When & Then
        assertThrows(CustomException.class,
                     () -> customRestTemplate.getForEntity(String.format(URI_FORMAT, port, randomId), User.class));
    }
}