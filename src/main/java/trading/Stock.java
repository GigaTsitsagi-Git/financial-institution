package trading;

import java.math.BigDecimal;

public class Stock {

    private String companyName;
    private int shares;
    private BigDecimal pricePerShare;

    public Stock(String companyName, int shares, BigDecimal pricePerShare) {
        this.companyName = companyName;
        this.shares = shares;
        this.pricePerShare = pricePerShare;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getShares() {
        return shares;
    }

    public BigDecimal getPricePerShare() {
        return pricePerShare;
    }

    public void setPricePerShare(BigDecimal pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    public BigDecimal getNetWorth() {
        return BigDecimal.valueOf(shares).multiply(pricePerShare);
    }
}
