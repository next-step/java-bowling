package bowling.domain;

import static bowling.util.Const.GUTTER_NUMBER;
import static bowling.util.Const.MAX_PIN;

public enum ScoreType {
    STRIKE("X"), SPARE("|/"), MISS("|"), GUTTER("-|-"), SECOND("|"), NULL(" ");
    private final String symbol;

    ScoreType(String symbol) {
        this.symbol = symbol;
    }

    public static ScoreType of(int first) {
        if (first == MAX_PIN) {
            return STRIKE;
        }
        return SECOND;
    }

    public static ScoreType of(int first, int second) {
        int total = first + second;
        if (total == MAX_PIN) {
            return SPARE;
        }
        if (total == GUTTER_NUMBER) {
            return GUTTER;
        }
        if (total < MAX_PIN) {
            return MISS;
        }
        throw new RuntimeException("unreachable score: " + total);
    }

    public String toSymbol() {
        return this.symbol;
    }
}
