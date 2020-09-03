package bowling.domain.core;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public final class Pins {
    static final int MIN_FALLEN_PIN_COUNT = 0;
    static final int MAX_FALLEN_PIN_COUNT = 10;
    static final String ERROR_MESSAGE = "지정된 넘어진 핀 갯수가 잘못되었습니다.";
    private static final List<Pins> cachedPins;
    private final int fallenPins;

    static {
        final List<Pins> pins = IntStream.rangeClosed(MIN_FALLEN_PIN_COUNT, MAX_FALLEN_PIN_COUNT)
                                         .mapToObj(Pins::new)
                                         .collect(toList());
        cachedPins = Collections.unmodifiableList(pins);
    }

    private Pins(int fallenPins) {
        this.fallenPins = fallenPins;
    }

    public static Pins of(int fallenPinCount) {
        verifyPins(fallenPinCount);
        return cachedPins.get(fallenPinCount);
    }

    private static void verifyPins(int fallenPinCount) {
        if (MIN_FALLEN_PIN_COUNT > fallenPinCount || MAX_FALLEN_PIN_COUNT < fallenPinCount) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    int getFallenPins() {
        return fallenPins;
    }

    public static boolean isStrike(int fallenPinCount) {
        return MAX_FALLEN_PIN_COUNT == fallenPinCount;
    }

    boolean isStrike() {
        return isStrike(getFallenPins());
    }

    boolean isSpare(Pins secondRoll) {
        return MAX_FALLEN_PIN_COUNT == (getFallenPins() + secondRoll.getFallenPins());
    }

    boolean isMiss(Pins secondRoll) {
        return !isSpare(secondRoll) && !(isGutter() && secondRoll.isGutter());
    }

    boolean isGutter() {
        return MIN_FALLEN_PIN_COUNT == getFallenPins();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pins pins = (Pins) o;
        return getFallenPins() == pins.getFallenPins();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getFallenPins());
    }

    @Override
    public String toString() {
        return "Pins(" + fallenPins + ')';
    }
}
