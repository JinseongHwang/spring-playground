package me.study.stockconcurrency.facade;

import java.util.concurrent.TimeUnit;
import me.study.stockconcurrency.service.StockServiceInterface;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RedissonLockStockFacade {

    private final Logger logger = LoggerFactory.getLogger(RedissonLockStockFacade.class);

    private final RedissonClient redissonClient;
    private final StockServiceInterface stockService;

    public RedissonLockStockFacade(RedissonClient redissonClient, StockServiceInterface stockService) {
        this.redissonClient = redissonClient;
        this.stockService = stockService;
    }

    public void decrease(Long key, Long quantity) {
        final RLock lock = redissonClient.getLock(key.toString());

        try {
            final boolean available = lock.tryLock(5, 1, TimeUnit.SECONDS);

            if (!available) {
                logger.error(">>>>> lock 획득 실패");
                return;
            }

            stockService.decrease(key, quantity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
