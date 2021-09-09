package bowling.bowlingdrawing.domain;

import bowling.bowlingdrawing.exception.CustomException;

import java.util.Objects;

public class Pitching {

    public static final int MINIMUM_OF_PINS = 0;
    public static final int MAXIMUM_OF_PINS = 10;

    private final int pins;

    public Pitching(int pins) {
        validateOutOfRange(pins);
        this.pins = pins;
    }

    private void validateOutOfRange(int pins) {
        if (MINIMUM_OF_PINS > pins || MAXIMUM_OF_PINS < pins) {
            throw new CustomException("pin 허용 범위 초과");
        }
    }

    public boolean isTen() {
        return pins == MAXIMUM_OF_PINS;
    }

    public int sum(Pitching pitching) {
        if (pitching == null) {
            return this.pins;
        }
        return this.pins + pitching.pins;
    }

    public int score() {
        return pins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pitching)) return false;
        Pitching pitching = (Pitching) o;
        return pins == pitching.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
