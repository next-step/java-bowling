package bowling.domain.pin;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum PinExpression {
    ZERO(0, "-"),
    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "X");

    private static final Map<Integer, String> pinExpressionMap = Arrays.stream(values())
            .collect(Collectors.toMap(PinExpression::value, PinExpression::symbol));

    private final int value;
    private final String symbol;


    PinExpression(int value, String symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    public static String convert(int value) {
        return pinExpressionMap.get(value);
    }

    private int value() {
        return value;
    }

    private String symbol() {
        return symbol;
    }
}
