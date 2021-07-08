package me.jinseonghwang.jwttutorial.repository;

import java.util.Optional;
import me.jinseonghwang.jwttutorial.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "authorities") // 쿼리 수행 시 Lazy 조회가 아니고 Eager 조회로 authorities 정보를 같이 가져온다.
    Optional<User> findOneWithAuthoritiesByUsername(String username);
}
