package com.ederfaria.stockquotemanager;

import com.ederfaria.stockquotemanager.retrofit.StockManagerApiController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class BasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializationNotification() {
        StockManagerApiController apiController = new StockManagerApiController();
        apiController.registerOnStockManager();
    }
}
