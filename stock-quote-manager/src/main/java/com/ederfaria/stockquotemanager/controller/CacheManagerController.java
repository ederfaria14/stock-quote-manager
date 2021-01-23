package com.ederfaria.stockquotemanager.controller;

import com.ederfaria.stockquotemanager.model.service.CacheManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stockcache")
public class CacheManagerController {

    @Autowired
    private CacheManagerService service;

    @DeleteMapping
    public ResponseEntity<String> getAllStockQuotes() {
        service.clearMap();
        return ResponseEntity.ok("CACHE IS NOW CLEANED");
    }
}
