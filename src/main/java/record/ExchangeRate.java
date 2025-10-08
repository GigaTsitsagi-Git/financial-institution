package record;

import java.math.BigDecimal;

public record ExchangeRate(String fromCurrency, String toCurrency, BigDecimal rate) {

    public BigDecimal convert(BigDecimal amount) {
        return amount.multiply(rate);
    }

}
