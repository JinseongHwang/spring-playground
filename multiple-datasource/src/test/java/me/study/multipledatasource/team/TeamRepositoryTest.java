package me.study.multipledatasource.team;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional("secondTransactionManager")
@SpringBootTest
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @DisplayName("Team 삽입")
    @Rollback(value = false)
    @Test
    void insertTeam() throws Exception {
        // Given
        final Team team = new Team("teamA");

        // When
        final Team savedTeam = teamRepository.save(team);

        // Then
        assertEquals(team.getName(), savedTeam.getName());
    }
}