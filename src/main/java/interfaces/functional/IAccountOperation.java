package interfaces.functional;

import java.math.BigDecimal;

@FunctionalInterface
public interface IAccountOperation {

    BigDecimal apply(BigDecimal balance, BigDecimal amount);
}
