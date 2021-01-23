package com.ederfaria.stockquotemanager.model.entity;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_quote")
public class StockQuote {

    @Id
    private String id;

    @ElementCollection(targetClass = String.class)
    private Map<String, String> quotes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getQuotes() {
        return quotes;
    }

    public void setQuotes(Map<String, String> quotes) {
        this.quotes = quotes;
    }

    public StockQuote() {
        super();
    }

    public StockQuote(String id) {
        super();
        this.id = id;
    }

    @Override
    public String toString() {
        return "StockQuote {id=" + id + ", quotes=" + quotes + "}";
    }

}
