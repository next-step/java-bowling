package bowling.domain.pin;

import java.util.Objects;

public abstract class FramePins {
    public static final int MIN_PINS_PER_FRAME = 0;
    public static final int MAX_PINS_PER_FRAME = 10;

    final Pins firstPins;
    Pins secondPins;

    public FramePins(Pins firstPins, Pins secondPins) {
        Objects.requireNonNull(firstPins, "첫 번째 투구가 유효하지 않습니다.");
        validateSum(firstPins, secondPins);
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    private void validateSum(Pins firstPins, Pins secondPins) {
        if (Pins.sum(firstPins, secondPins) > MAX_PINS_PER_FRAME) {
            throw new IllegalArgumentException("한 프레임 당 투구의 합은 " + MAX_PINS_PER_FRAME + "개를 초과할 수 없습니다.");
        }
    }

    public FramePins createBy(Pins secondPins) {
        this.secondPins = secondPins;

        if (isSpare()) {
            return Spare.of(firstPins, secondPins);
        }

        if (isGutter()) {
            return Gutter.of();
        }

        return Miss.of(firstPins, secondPins);
    }

    private boolean isSpare() {
        return Pins.sum(firstPins, secondPins) == MAX_PINS_PER_FRAME;
    }

    private boolean isGutter() {
        return Pins.sum(firstPins, secondPins) == MIN_PINS_PER_FRAME;
    }

    public int score() {
        return Pins.sum(firstPins, secondPins);
    }

    public abstract boolean hasNext();

    @Override
    public String toString() {
        return firstPins.toString() + "|" + secondPins.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FramePins framePins = (FramePins) o;
        return Objects.equals(firstPins, framePins.firstPins) &&
                Objects.equals(secondPins, framePins.secondPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPins, secondPins);
    }
}
