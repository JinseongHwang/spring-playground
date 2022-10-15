package me.study.stockconcurrency.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import me.study.stockconcurrency.domain.Stock;
import me.study.stockconcurrency.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StockServiceTest {

    @Autowired
    private StockServiceInterface stockService;

    @Autowired
    private StockServiceInterface pessimisticLockStockService;

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    public void before() {
        final Stock stock = new Stock(1L, 1L, 100L);
        stockRepository.saveAndFlush(stock);
    }

    @AfterEach
    public void after() {
        stockRepository.deleteAll();
    }

    @Test
    void stock_decrease() throws Exception {
        stockService.decrease(1L, 1L); // 100 - 1 = 99

        final Stock stock = stockRepository.findById(1L)
                                           .orElseThrow(IllegalArgumentException::new);
        assertThat(stock.getQuantity()).isEqualTo(99L);
    }

    /**
     * Race condition이 발생했기 때문이다.
     * 똑같이 현재 재고 100을 조회해가서, 1을 줄인 99로 set 하는 쿼리가 여러번 발생했기 때문이다.
     */
    @Test
    void 동시에_100개_요청() throws Exception {
        final int threadCount = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(32);
        final CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    stockService.decrease(1L, 1L);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        final Stock stock = stockRepository.findById(1L)
                                           .orElseThrow(IllegalArgumentException::new);

        // 100 - (1 * 100) = 0
        assertThat(stock.getQuantity()).isNotEqualTo(0L);
    }

    @Test
    void 동시에_100개_요청_Pessimistic_Lock_활용() throws Exception {
        final int threadCount = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(32);
        final CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    pessimisticLockStockService.decrease(1L, 1L);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        final Stock stock = stockRepository.findById(1L)
                                           .orElseThrow(IllegalArgumentException::new);

        // 100 - (1 * 100) = 0
        assertThat(stock.getQuantity()).isEqualTo(0L);
    }
}