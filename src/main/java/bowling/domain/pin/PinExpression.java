package bowling.domain.pin;

import bowling.domain.state.StateExpression;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum PinExpression {

    ZERO(0, StateExpression.GUTTER),
    TEN(10, StateExpression.STRIKE);

    private final int count;
    private final String symbol;

    private static final Map<Integer, String> SYMBOLS =
            Arrays.stream(values())
                    .collect(Collectors.toMap(PinExpression::getCount, PinExpression::getSymbol));

    PinExpression(final int count, final String symbol) {
        this.count = count;
        this.symbol = symbol;
    }

    public static String convert(final int count) {
        return SYMBOLS.getOrDefault(count, String.valueOf(count));
    }

    private int getCount() {
        return count;
    }

    private String getSymbol() {
        return symbol;
    }
}
