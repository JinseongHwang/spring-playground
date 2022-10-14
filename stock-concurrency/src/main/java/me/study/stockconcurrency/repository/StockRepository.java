package me.study.stockconcurrency.repository;

import me.study.stockconcurrency.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
