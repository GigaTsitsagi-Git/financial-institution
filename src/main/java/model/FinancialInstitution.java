package model;

import transaction.FinancialExchange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class FinancialInstitution {

    private static final Logger logger = LogManager.getLogger(FinancialInstitution.class);
    private static int institutionCount = 0;

    private String name;
    private String address;
    private LocalDate registrationDate;
    private LocalDateTime lastUpdated;

    private List<Bank> banks;
    private Map<String, FinancialExchange> financialExchanges = new HashMap<>();

    public FinancialInstitution(String name, String address) {
        this.name = name;
        this.address = address;
        this.registrationDate = LocalDate.now();
        this.lastUpdated = LocalDateTime.now();
        banks = new ArrayList<>();
        institutionCount++;
    }

    public void updateInstitution(String newAddress) {
        this.address = newAddress;
        this.lastUpdated = LocalDateTime.now();    // refresh timestamp
    }

    public void addFinancialExchange(FinancialExchange financialExchange) {
        financialExchanges.put(financialExchange.getName(), financialExchange);
    }

    public void addBank(Bank bank) {
        banks.add(bank);
    }

    public void printFinancialExchange() {
        if (financialExchanges.isEmpty()) {
            logger.info("The list is empty");
        }
        for (Map.Entry<String, FinancialExchange> financialExchangeEntry : financialExchanges.entrySet()) {
            logger.info("{} => {}", financialExchangeEntry.getKey(), financialExchangeEntry.getValue());
        }
    }

    public FinancialExchange getFinancialExchangeWithKey(String key) {
        return financialExchanges.get(key);
    }

    public void setName(String name) {
        this.name = name;
        lastUpdated = LocalDateTime.now();
    }

    public String getName() {
        return this.name;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setAddress(String address) {
        this.address = address;
        lastUpdated = LocalDateTime.now();
    }

    public String getAddress() {
        return this.address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, registrationDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        FinancialInstitution that = (FinancialInstitution) obj;

        return name.equals(that.name)
                && address.equals(that.address)
                && registrationDate.equals(that.registrationDate);
    }
}
