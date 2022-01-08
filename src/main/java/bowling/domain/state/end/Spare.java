package bowling.domain.state.end;

import bowling.domain.Pins;
import bowling.domain.state.ThrowingState;

import java.util.Objects;

public class Spare extends EndedState {

    private final Pins pins;
    private final Pins secondPins;

    private Spare(Pins pins, Pins secondPins) {
        this.pins = pins;
        this.secondPins = secondPins;
    }

    public static ThrowingState create(Pins pins, Pins secondPins) {
        validate(pins, secondPins);
        return new Spare(pins, secondPins);
    }

    @Override
    public String symbol() {
        return pins.getPinCount() + "|" + secondPins.getPinCount();
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    private static void validate(Pins pins, Pins secondPins) {
        if (!pins.isSpare(secondPins)) {
            throw new IllegalArgumentException(String.format("스페어는 두번의 투구로 %d개 모두 쓰러트려야 합니다.", Pins.MAX_RANGE));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return Objects.equals(pins, spare.pins) && Objects.equals(secondPins, spare.secondPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, secondPins);
    }

    @Override
    public String toString() {
        return "Spare{" +
                "pins=" + pins +
                ", secondPins=" + secondPins +
                '}';
    }
}
