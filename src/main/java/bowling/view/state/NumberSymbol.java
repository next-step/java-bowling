package bowling.view.state;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum NumberSymbol {
    ZERO(0, "-"),
    TEN(10, "X");

    private static final Map<Integer, String> NUMBER_SYMBOLS =
            Arrays.stream(values())
                    .collect(Collectors.toMap(NumberSymbol::getNumber, NumberSymbol::getSymbol));

    private final int number;
    private final String symbol;

    NumberSymbol(int number, String symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    private int getNumber() {
        return number;
    }

    private String getSymbol() {
        return symbol;
    }

    public static String convert(int number) {
        return NUMBER_SYMBOLS.getOrDefault(number, String.valueOf(number));
    }
}
