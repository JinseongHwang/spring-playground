package me.study.stockconcurrency.facade;

import me.study.stockconcurrency.repository.LockRepository;
import me.study.stockconcurrency.service.StockServiceInterface;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class NamedLockStockFacade {

    private final LockRepository lockRepository;

    private final StockServiceInterface stockService;

    public NamedLockStockFacade(LockRepository lockRepository, StockServiceInterface stockService) {
        this.lockRepository = lockRepository;
        this.stockService = stockService;
    }

    @Transactional
    public void decrease(Long id, Long quantity) {
        try {
            lockRepository.getLock(id.toString());
            stockService.decrease(id, quantity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockRepository.releaseLock(id.toString());
        }
    }
}
