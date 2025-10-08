package enums;

import java.math.BigDecimal;

public enum CurrencyType {

    USD("United States Dollar", "$"),
    EUR("Euro", "€"),
    GBP("Pound Sterling", "£"),
    GEL("Georgina Lari", "₾");

    private final String fullName;
    private final String symbol;

    static {
        System.out.println("CurrencyType enum loaded into memory.");
    }

    CurrencyType(String fullName, String symbol) {
        this.fullName = fullName;
        this.symbol = symbol;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSymbol() {
        return symbol;
    }

    public String formatAmount(BigDecimal amount) {
        return String.format("%s%.2f", symbol, amount);
    }
}
