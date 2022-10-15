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

    @Transactional(propagation = Propagation.REQUIRES_NEW) // 부모 트랜잭션과 별도로 실행되어야 하기 때문
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
