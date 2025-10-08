package model;

public final class Currency {

    private final String CODE;
    private final String SYMBOL;

    public Currency(String CODE, String SYMBOL) {
        this.CODE = CODE;
        this.SYMBOL = SYMBOL;
    }

    public String getCODE() {
        return CODE;
    }

    public String getSYMBOL() {
        return SYMBOL;
    }

    @Override
    public String toString() {
        return CODE + ", " + SYMBOL;
    }
}
