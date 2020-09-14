package bowling.domain;

public enum BowlResult {
    STRIKE("X"),
    SPARE("/"),
    MISS("%d"),
    GUTTER("-"),
    DEFAULT("%d");

    private String expression;

    BowlResult(String expression) {
        this.expression = expression;
    }

    public static String of(int previous) {
        if (previous == 10) {
            return BowlResult.STRIKE.expression;
        } else if (previous == 0) {
            return BowlResult.GUTTER.expression;
        }
        return String.valueOf(previous);
    }

    public static String of(Integer previous, Integer now) {
        return BowlResult.SPARE;
    }
}
