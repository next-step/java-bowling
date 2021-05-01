package bowling.domain;

import java.util.Arrays;

public enum PointSymbol {
    STRIKE('X', 10),
    SPARE('/'),
    GUTTER('-', 0),
    ONE('1', 1),
    TWO('2', 2),
    THREE('3', 3),
    FOUR('4', 4),
    FIVE('5', 5),
    SIX('6', 6),
    SEVEN('7', 7),
    EIGHT('8', 8),
    NINE('9', 9),
    EMPTY(' ');

    private final char symbol;
    private final int number;

    PointSymbol(char symbol) {
        this(symbol, -1);
    }

    PointSymbol(char symbol, int number) {
        this.symbol = symbol;
        this.number = number;
    }

    public char symbol() {
        return symbol;
    }

    public static PointSymbol valueOf(Pinfall pinfall) {
        return Arrays.stream(values())
                .filter(pointSymbol -> pointSymbol.isMatch(pinfall.number()))
                .findFirst()
                .orElse(EMPTY);
    }

    private boolean isMatch(int number) {
        return this.number == number;
    }
}
