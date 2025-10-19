package transaction;

import trading.Stock;
import trading.Trader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FinancialExchange {

    private static final Logger logger = LogManager.getLogger(FinancialExchange.class);

    private String name;

    private List<Stock> listedStocks;
    private List<Trader> traders;

    public FinancialExchange(String name) {
        this.name = name;
        listedStocks = new ArrayList<>();
        traders = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Stock> getListedStocks() {
        return listedStocks;
    }

    public List<Trader> getTraders() {
        return traders;
    }

    public void addStock(Stock stock) {
        listedStocks.add(stock);
    }

    public void addTrader(Trader trader) {
        traders.add(trader);
    }

    public void showExchangeInfo() {
        logger.info("Exchange: {}", name);
        logger.info("Listed Stocks: {}", (listedStocks.size() - 1));
        logger.info("Active Traders: {}", (traders.size() - 1));
    }
}
