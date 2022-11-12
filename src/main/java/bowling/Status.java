package bowling;

import java.util.Arrays;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.isNull;

public enum Status {
    STRIKE("X", 0, TRUE, FALSE),
    SPARE("/", 1, TRUE, FALSE),
    MISS("1", 1, FALSE, FALSE),
    GUTTER("-", null, FALSE, TRUE),
    NONE("", 3, TRUE, TRUE)
    ;

    private final String symbol;
    private final Integer order;
    private final boolean allPinsDown;
    private final boolean noPinsDown;

    Status(String symbol, Integer order, boolean allPinsDown, boolean noPinsDown) {
        this.symbol = symbol;
        this.order = order;
        this.allPinsDown = allPinsDown;
        this.noPinsDown = noPinsDown;
    }

    public static Status findStatus(int order, boolean allPinsDown, boolean noPinsDown) {
        return Arrays.stream(values())
                .filter(s ->
                        (isNull(s.order) || s.order.equals(order))
                                && s.allPinsDown == allPinsDown
                                && s.noPinsDown == noPinsDown)
                .findFirst()
                .orElse(NONE);
    }

    public String symbol() {
        return symbol;
    }

}
