package bowling.domain.value;

import bowling.utils.Preconditions;

import java.util.Objects;

public class Pins {
    private static final int ZERO_COUNT = 0;
    private static final int STRIKE_COUNT = 10;

    private final int pins;

    public Pins(int pins) {
        Preconditions.checkMinimumSize(pins, ZERO_COUNT,
                                       String.format("쓰러트린 핀의 갯수는 %s 이상 이어야 합니다.", ZERO_COUNT));
        Preconditions.checkMaximumSize(pins, STRIKE_COUNT,
                                       String.format("쓰러트린 핀의 갯수는 %s 이하 이어야 합니다.", STRIKE_COUNT));

        this.pins = pins;
    }

    public static Pins from(int pins) {
        return new Pins(pins);
    }

    public boolean isGutter() {
        return ZERO_COUNT == pins;
    }

    public boolean isStrike() {
        return STRIKE_COUNT == pins;
    }

    public boolean isSpare(Pins pins) {
        return STRIKE_COUNT == (this.pins + pins.getPins());
    }

    public int add(Pins pins) {
        if (Objects.isNull(pins)) {
            return this.pins;
        }

        return this.pins + pins.pins;
    }

    public int getPins() {
        return pins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins1 = (Pins) o;
        return pins == pins1.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
