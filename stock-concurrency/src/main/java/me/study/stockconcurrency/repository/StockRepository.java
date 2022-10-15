package me.study.stockconcurrency.repository;

import java.util.Optional;
import javax.persistence.LockModeType;
import me.study.stockconcurrency.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProductId(Long productId);

    /**
     * PESSIMISTIC LOCK : 비관적 락
     * 충돌이 빈번하게 일어난다면 Optimistic Lock보다 성능이 좋을 수도 있다.
     * select ~ for update 쿼리로 락을 걸기 때문에 데이터 정합성에 도움을 준다.
     * 하지만 별도의 락을 갖기 때문에 성능 감소가 발생할 수 있다.
     *
     * Pessimistic Lock은 이미 존재하는 데이터의 정합성을 유지하는 것이 목적이므로, 동일한 데이터가 동시에 중복 저장되는 것을 막는 것을 불가능하다.
     * 해당 문제는 Named Lock이나 Redis를 이용해서 해결할 수 있다.
     */
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Stock s where s.id = :id")
    Stock findByIdWithPessimisticLock(@Param("id") Long id);

    /**
     * OPTIMISTIC LOCK : 낙관적 락
     * 별도의 락을 잡지 않으므로 PESSIMISTIC LOCK 보다 성능 측면에서 좋을 수 있다.
     * 단, 버전이 달라서 Update 에 실패했을 경우에는 재시도 로직을 개발자가 직접 작성해줘야 한다는 단점이 있다.
     * 충돌이 빈번하게 발생할 것으로 예상한다면, OPTIMISTIC LOCK 보다는 PESSIMISTIC LOCK 을 사용하는 것이 더 좋을 수 있다.
     */
    @Lock(value = LockModeType.OPTIMISTIC)
    @Query("select s from Stock s where s.id = :id")
    Stock findByIdWithOptimisticLock(@Param("id") Long id);
}
