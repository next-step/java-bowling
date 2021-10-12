package bowling.domain;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PinCount {
    private static final String SEPARATOR = "|";
    private static final String SPARE = "/";
    private static final String GUTTER = "-";

    public static final int MAX = 10;

    public static final int MIN = 0;

    private static final Map<Integer, PinCount> pinCountMap =
            Collections.unmodifiableMap(IntStream.rangeClosed(MIN, MAX)
                    .boxed()
                    .collect(Collectors.toMap(Function.identity(), PinCount::new)));

    public static final PinCount MAX_PINS = of(MAX);

    private final int pinCount;

    public static PinCount of(int pins) {
        PinCount pinCount = pinCountMap.get(pins);
        if (Objects.isNull(pinCount)) {
            throw new IllegalArgumentException("핀 수는 " + MIN + "~" + MAX + " 사이 값이어야합니다. pins : " + pins);
        }
        return pinCount;
    }

    private PinCount(int pins) {
        this.pinCount = pins;
    }

    public PinCount plus(final PinCount pinCount) {
        return of(this.pinCount + pinCount.pinCount);
    }

    public boolean isMaxPinCount() {
        return Objects.equals(pinCount, MAX);
    }

    public boolean isSpare(final PinCount pinCount) {
        PinCount sum = plus(pinCount);
        return Objects.equals(sum, MAX_PINS);
    }

    public int getValue() {
        return pinCount;
    }

    public String showIndication() {
        if (isGutter()) {
            return GUTTER;
        }
        return String.valueOf(pinCount);
    }

    public String showIndication(final PinCount pinCount) {
        if (isSpare(pinCount)) {
            return showIndication() + SEPARATOR + SPARE;
        }
        return showIndication() + SEPARATOR + pinCount.showIndication();
    }

    private boolean isGutter() {
        return Objects.equals(pinCount, MIN);
    }

    @Override
    public String toString() {
        return String.valueOf(pinCount);
    }
}