package me.study.stockconcurrency.facade;

import me.study.stockconcurrency.repository.RedisLockRepository;
import me.study.stockconcurrency.service.StockServiceInterface;
import org.springframework.stereotype.Component;

@Component
public class LettuceLockStockFacade {

    private final RedisLockRepository redisLockRepository;
    private final StockServiceInterface stockService;

    public LettuceLockStockFacade(RedisLockRepository redisLockRepository, StockServiceInterface stockService) {
        this.redisLockRepository = redisLockRepository;
        this.stockService = stockService;
    }

    public void decrease(Long key, Long quantity) throws InterruptedException {
        while (redisLockRepository.lock(key) == Boolean.FALSE) {
            Thread.sleep(100);
        }

        try {
            stockService.decrease(key, quantity);
        } finally {
            redisLockRepository.unlock(key);
        }
    }
}
