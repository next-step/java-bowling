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

    public static Status findStatus(int order, Point current, Point sum) {
        if (current.equals(Point.All_KNOCK_DOWN_POINT)) {
            return STRIKE;
        }
        if (order == 1 && current.isSmallerThan(Point.All_KNOCK_DOWN_POINT) && sum.equals(Point.All_KNOCK_DOWN_POINT)) {
            return SPARE;
        }
        if (order == 1 && sum.isSmallerThan(Point.All_KNOCK_DOWN_POINT)) {
            return MISS;
        }
        if (order == 0 && current.equals(Point.NO_KNOCK_DOWN_POINT) && sum.equals(Point.NO_KNOCK_DOWN_POINT)) {
            return GUTTER;
        }
        return NONE;
    }

    public String symbol() {
        return symbol;
    }

}
