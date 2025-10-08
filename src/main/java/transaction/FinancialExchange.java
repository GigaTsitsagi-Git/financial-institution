package transaction;

import trading.Stock;
import trading.Trader;

import java.util.ArrayList;
import java.util.List;

public class FinancialExchange {

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
        System.out.println("Exchange: " + name);
        System.out.println("Listed Stocks: " + (listedStocks.size() - 1));
        System.out.println("Active Traders: " + (traders.size() - 1));
    }
}
