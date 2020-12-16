package bowling.view.output;

import java.util.Arrays;

public enum SymbolMap {
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
    TEN(10, OutputView.STRIKE),
    ;

    private final int key;
    private final String symbol;

    SymbolMap(int key, String symbol) {
        this.symbol = symbol;
        this.key = key;
    }

    public static String getSymbol(final int key) {
        return Arrays.stream(SymbolMap.values())
                .filter(symbolMap -> symbolMap.key == key)
                .findAny()
                .map(symbolMap -> symbolMap.symbol)
                .orElseThrow(IllegalArgumentException::new);
    }
}
