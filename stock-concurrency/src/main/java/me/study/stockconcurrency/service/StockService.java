package me.study.stockconcurrency.service;

import me.study.stockconcurrency.domain.Stock;
import me.study.stockconcurrency.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public synchronized void decrease(Long id, Long quantity) {
        // 1. get stock
        final Stock stock = stockRepository.findById(id)
                                           .orElseThrow();

        // 2. decrease stock quantity
        stock.decrease(quantity);

        // 3. save
        stockRepository.saveAndFlush(stock);
    }
}
