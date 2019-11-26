package bowling.domain;

import java.util.Arrays;

public enum Symbol {

    ONE("1", 1, false),
    TWO("2", 2, false),
    THREE("3", 3, false),
    FOUR("4", 4, false),
    FIVE("5", 5, false),
    SIX("6", 6, false),
    SEVEN("7", 7, false),
    EIGHT("8", 8, false),
    NINE("9", 9, false),
    STRIKE("X", 10, false),
    SPARE("/", 10, true),
    GUTTER("-", 0, false);

    private String symbol;
    private int pin;
    private boolean twoPins;

    Symbol(String symbol, int pin, boolean twoPins) {
        this.symbol = symbol;
        this.pin = pin;
        this.twoPins = twoPins;
    }

    public static Symbol findByPin(int pin) {
        return Arrays.stream(Symbol.values())
                .filter(symbol -> symbol.pin == pin)
                .filter(symbol -> symbol.twoPins == false)
                .findFirst()
                .orElse(null);
    }

    public static Symbol findByPins(int first, int second) {
        if (first + second == 10) {
            return Symbol.SPARE;
        }
        return findByPin(second);
    }


}
