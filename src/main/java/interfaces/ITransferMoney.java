package interfaces;

import java.math.BigDecimal;

public interface ITransferMoney {

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    void printMoneyTransferred();
}
