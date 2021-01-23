package com.ederfaria.stockquotemanager.model.repository;

import com.ederfaria.stockquotemanager.model.entity.StockQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Faria
 */
@Repository
public interface StockQuoteRepository extends JpaRepository<StockQuote, Long>{
    
}
