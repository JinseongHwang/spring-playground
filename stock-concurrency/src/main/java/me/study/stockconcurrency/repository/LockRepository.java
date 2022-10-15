package me.study.stockconcurrency.repository;

import me.study.stockconcurrency.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Namee Lock은 이름을 가진 메타데이터 락이다.
 * 이름을 가진 락을 획득한 후, 해제될 때까지 다른 세션은 이 락을 획득할 수 없다.
 * Namee Lock은 주로 분산 락을 구현할 때 사용한다.
 * Pessimistic Lock은 Time out을 구현하기 까다롭지만, Named Lock은 손쉽게 구현 가능하다.
 * 트랜잭션 종료 시 락이 자동으로 해제되지 않아서, 트랜잭션 종료 시 별도로 반드시 락 해제를 해주거나 선점 시간이 끝나야 해제된다.
 */
public interface LockRepository extends JpaRepository<Stock, Long> {

    // Timeout : 2초
    // 이미 Lock이 걸려 있으면(사용 중이면) 2초만 대기한다.
    @Query(value = "select get_lock(:key, 2)", nativeQuery = true) // MySQL 문법
    void getLock(@Param("key") String key);

    @Query(value = "select release_lock(:key)", nativeQuery = true) // MySQL 문법
    void releaseLock(@Param("key") String key);
}
