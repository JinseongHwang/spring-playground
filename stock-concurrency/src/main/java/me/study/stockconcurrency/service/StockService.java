package me.study.stockconcurrency.service;

import me.study.stockconcurrency.domain.Stock;
import me.study.stockconcurrency.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService implements StockServiceInterface {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * Propagation.REQUIRES_NEW 을 사용하는 이유
     * 부모 트랜잭션과 별도로 실행되어야 하기 때문이다.
     * 만약 Default 값인 Propagation.REQUIRED 을 사용하면 트랜잭션 커밋 전에 락이 풀리는 현상이 발생한다.
     * 그렇기 때문에 별도의 트랜잭션으로 분리를 해주어 정상적으로 트랜잭션 커밋이 된 이후에 락을 해제하도록 한다.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public synchronized void decrease(Long id, Long quantity) {
        // 1. get stock
        final Stock stock = stockRepository.findById(id)
                                           .orElseThrow(IllegalArgumentException::new);

        // 2. decrease stock quantity
        stock.decrease(quantity);

        // 3. save
        stockRepository.saveAndFlush(stock);
    }
}
