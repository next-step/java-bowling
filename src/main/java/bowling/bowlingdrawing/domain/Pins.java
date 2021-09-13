package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.exception.CustomException;

import java.util.Objects;

public class Pins {

    public static final int MINIMUM_PINS = 0;
    public static final int MAXIMUM_PINS = 10;

    private final int pins;

    public Pins(int pins) {
        validateOutOfRange(pins);
        this.pins = pins;
    }

    private void validateOutOfRange(int pins) {
        if (pins < MINIMUM_PINS || pins > MAXIMUM_PINS) {
            throw new CustomException("pins의 개수가 허용 범위를 벗어났습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pins)) return false;
        Pins pins1 = (Pins) o;
        return pins == pins1.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
