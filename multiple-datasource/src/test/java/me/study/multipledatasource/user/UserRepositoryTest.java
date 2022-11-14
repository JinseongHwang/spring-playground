package me.study.multipledatasource.user;

import static org.junit.jupiter.api.Assertions.*;

import me.study.multipledatasource.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional("firstTransactionManager")
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("User 삽입")
    @Rollback(value = false)
    @Test
    void insertUser() throws Exception {
        // Given
        final User user = new User("Jinseong");

        // When
        final User savedUser = userRepository.save(user);

        // Then
        assertEquals(user.getName(), savedUser.getName());
    }

}