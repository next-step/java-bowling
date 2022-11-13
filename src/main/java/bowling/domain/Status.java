package bowling.domain;

public enum Status {
    STRIKE("X"),
    SPARE("/"),
    MISS("1"),
    GUTTER("-"),
    NONE("");

    private final String symbol;

    Status(String symbol) {
        this.symbol = symbol;
    }

    public static Status findStatus(int order, Pin current, Pin sum) {
        if (current.equals(Pin.All_KNOCK_DOWN_PIN)) {
            return STRIKE;
        }
        if (order == 1 && current.isSmallerThan(Pin.All_KNOCK_DOWN_PIN) && sum.equals(Pin.All_KNOCK_DOWN_PIN)) {
            return SPARE;
        }
        if (order == 1 && sum.isSmallerThan(Pin.All_KNOCK_DOWN_PIN)) {
            return MISS;
        }
        if (order == 0 && current.equals(Pin.NO_KNOCK_DOWN_PIN) && sum.equals(Pin.NO_KNOCK_DOWN_PIN)) {
            return GUTTER;
        }
        return NONE;
    }

    public String symbol() {
        return symbol;
    }

}
