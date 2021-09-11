package bowling.domain.pin;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Pins {

    private static final int FIRST_PIN_INDEX = 0;
    private static final int SECOND_PIN_INDEX = 1;
    private static final int THIRD_PIN_INDEX = 2;
    private static final int MAX_PINS_SIZE = 3;
    private static final int MAX_TWO_PINS_SUM_IF_NOT_STRIKE = 10;

    private final List<Pin> pins;

    private Pins(final List<Pin> pins) {
        this.pins = pins;
    }

    public static Pins of(final int... numbers) {
        List<Pin> pins = Arrays.stream(numbers)
                .mapToObj(Pin::valueOf)
                .collect(Collectors.toList());
        return new Pins(pins);
    }

    public static Pins of(final List<Pin> pins) {
        return new Pins(pins);
    }

    public void add(final int knockDownNumber) {
        pins.add(Pin.valueOf(knockDownNumber));
    }

    public boolean isEmpty() {
        return pins.isEmpty();
    }

    public boolean isStrike() {
        if (pins.isEmpty()) {
            return false;
        }
        return firstPin().isMaximum();
    }

    public boolean isSpare() {
        if (pins.size() < 2) {
            return false;
        }
        return firstPin().sum(secondPin()) == MAX_TWO_PINS_SUM_IF_NOT_STRIKE;
     }

    public boolean isSecondPinWrong() {
        if (pins.size() < 2) {
            return false;
        }
        if (isStrike()) {
            return false;
        }
        return firstPin().sum(secondPin()) > MAX_TWO_PINS_SUM_IF_NOT_STRIKE;
    }

    public boolean isThirdPinWrong() {
        if (pins.size() != MAX_PINS_SIZE) {
            return false;
        }
        if (secondPin().isMaximum()) {
            return false;
        }
        if (firstPin().sum(secondPin()) == MAX_TWO_PINS_SUM_IF_NOT_STRIKE) {
            return false;
        }
        return secondPin().sum(thirdPin()) > MAX_TWO_PINS_SUM_IF_NOT_STRIKE;
    }

    public Pin firstPin() {
        return pins.get(FIRST_PIN_INDEX);
    }

    public int firstPinNumber() {
        return firstPin().getKnockDownNumber();
    }

    public Pin secondPin() {
        return pins.get(SECOND_PIN_INDEX);
    }

    public Pin thirdPin() {
        return pins.get(THIRD_PIN_INDEX);
    }

    public int sum() {
        return pins.stream()
                .map(Pin::getKnockDownNumber)
                .reduce(0, Integer::sum);
    }

    public int size() {
        return pins.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins1 = (Pins) o;
        return Objects.equals(pins, pins1.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
