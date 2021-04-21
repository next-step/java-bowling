package bowling.domain;

import java.util.Arrays;

public enum ScoreSymbol {
    STRIKE(10, true, "x"),
    SPARE(10, false,"/"),
    GUTTER(0, false, "-"),
    MISS(-1, false, "");

    private int pin;
    private boolean isFirst;
    private String symbol;

    ScoreSymbol(int pin, boolean isFirst, String symbol) {
        this.pin = pin;
        this.isFirst = isFirst;
        this.symbol = symbol;
    }

    public static ScoreSymbol valueOf(int pin, boolean isFirst) {
        return Arrays.stream(values())
                .filter(scoreSymbol -> scoreSymbol.pin == pin && scoreSymbol.isFirst == isFirst)
                .findFirst()
                .orElse(MISS);
    }
}
