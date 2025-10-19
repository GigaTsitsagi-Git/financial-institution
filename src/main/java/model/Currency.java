package model;

import enums.CurrencyType;

import java.math.BigDecimal;

public final class Currency {

    private final String CODE;
    private final String SYMBOL;
    private CurrencyType currencyType;

    public Currency(String CODE, String SYMBOL) {
        this.CODE = CODE;
        this.SYMBOL = SYMBOL;
        this.currencyType = findCurrencyType(CODE);
    }

    public Currency(CurrencyType currencyType) {
        this.CODE = currencyType.name();
        this.SYMBOL = currencyType.getSymbol();
        this.currencyType = currencyType;
    }

    private CurrencyType findCurrencyType(String code) {
        for (CurrencyType type : CurrencyType.values()) {
            if (type.name().equals(code)) {
                return type;
            }
        }
        return null; // Unknown currency
    }

    public String getCODE() {
        return CODE;
    }

    public String getSYMBOL() {
        return SYMBOL;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public String formatAmount(BigDecimal amount) {
        if (currencyType != null) {
            return currencyType.formatAmount(amount);
        }
        return SYMBOL + amount.toString();
    }

    public String getFullName() {
        if (currencyType != null) {
            return currencyType.getFullName();
        }
        return CODE;
    }

    @Override
    public String toString() {
        if (currencyType != null) {
            return currencyType.getFullName() + " (" + SYMBOL + ")";
        }
        return CODE + ", " + SYMBOL;
    }
}
