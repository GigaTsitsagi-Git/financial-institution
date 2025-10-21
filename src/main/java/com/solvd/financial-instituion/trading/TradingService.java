package com.solvd.financial_instituion.trading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class TradingService {
    
    private static final Logger logger = LogManager.getLogger(TradingService.class);
    
    public void buyStock(String symbol, int quantity, BigDecimal price) {
        logger.info("Buying {} shares of {} at {}", quantity, symbol, price);
    }
    
    public void sellStock(String symbol, int quantity, BigDecimal price) {
        logger.info("Selling {} shares of {} at {}", quantity, symbol, price);
    }
    
    public BigDecimal getCurrentPrice(String symbol) {
        logger.info("Getting current price for {}", symbol);
        return BigDecimal.valueOf(100.50);
    }
}
