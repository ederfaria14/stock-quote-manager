package com.ederfaria.stockquotemanager.controller;

import com.ederfaria.stockquotemanager.model.entity.StockQuote;
import com.ederfaria.stockquotemanager.model.repository.StockQuoteRepository;
import com.ederfaria.stockquotemanager.model.service.CacheManagerService;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockQuoteController {

    @Autowired
    private StockQuoteRepository stockQuoteRepository;

    @Autowired
    private CacheManagerService cacheService;

    @GetMapping
    public List<StockQuote> getAllStockQuotes() {
        return this.stockQuoteRepository.findAll();
    }

    //@PostConstruct
    public void delete() {
        stockQuoteRepository.deleteAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockQuote> getStockQuoteById(@PathVariable(value = "id") String stockQuoteId) {
        Example<StockQuote> example = Example.of(new StockQuote(stockQuoteId));

        Optional<StockQuote> stockQuote = this.stockQuoteRepository.findOne(example);
        if (stockQuote.isPresent()) {
            return ResponseEntity.ok(stockQuote.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody StockQuote stockQuote) {
        System.out.println("STOCK_QUOTE - CREATE - " + stockQuote.toString());
        if (cacheService.containStock(stockQuote.getId())) {
            StockQuote ret = this.stockQuoteRepository.save(stockQuote);
            return ResponseEntity.ok("STOCK QUOTE CREATE SUCCESS - " + ret.toString());
        }
        return ResponseEntity.ok("STOCK QUOTE NOT REGISTERED IN STOCK MANAGER");
    }

}
