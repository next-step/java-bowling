package bowling.domain.state.end;

import bowling.domain.Pins;
import bowling.domain.frame.Score;
import bowling.domain.state.ThrowingState;

import java.util.Arrays;
import java.util.Objects;

public class Miss extends EndedState {

    private static final String FRAME_STATE_FORMAT = "%s|%s";
    private static final String GUTTER_SYMBOL = "-";

    private final Pins pins;
    private final Pins secondPins;

    private Miss(Pins pins, Pins secondPins) {
        this.pins = pins;
        this.secondPins = secondPins;
    }

    public static ThrowingState create(Pins pins, Pins secondPins) {
        validate(pins, secondPins);
        return new Miss(pins, secondPins);
    }

    @Override
    public String symbol() {
        if (pins.isGutter() && secondPins.isGutter()) {
            return String.format(FRAME_STATE_FORMAT, GUTTER_SYMBOL, GUTTER_SYMBOL);
        }
        if (pins.isGutter()) {
            return String.format(FRAME_STATE_FORMAT, GUTTER_SYMBOL, secondPins.getPinCount());
        }
        if (secondPins.isGutter()) {
            return String.format(FRAME_STATE_FORMAT, pins.getPinCount(), GUTTER_SYMBOL);
        }

        return String.format(FRAME_STATE_FORMAT, pins.getPinCount(), secondPins.getPinCount());
    }

    @Override
    public boolean isMiss() {
        return true;
    }

    private int sumScore() {
        return Arrays.stream(symbol().split("\\|"))
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private static void validate(Pins pins, Pins secondPins) {
        if (!pins.isMiss(secondPins)) {
            throw new IllegalArgumentException(String.format("미스는 두번의 투구로 %d개 미만이어야 합니다.", Pins.MAX_RANGE));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return Objects.equals(pins, miss.pins) && Objects.equals(secondPins, miss.secondPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, secondPins);
    }

    @Override
    public String toString() {
        return "Miss{" +
                "pins=" + pins +
                ", secondPins=" + secondPins +
                '}';
    }
}
