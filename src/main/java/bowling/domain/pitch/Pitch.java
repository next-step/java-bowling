package bowling.domain.pitch;

import bowling.exception.BusinessException;

import java.util.Objects;

public class Pitch {
    public static final int MINIMUM_COUNT_OF_PINS = 0;
    public static final int MAXIMUM_COUNT_OF_PINS = 10;
    public static final String SEPARATOR = "|";

    private final int countOfPins;
    private final int remains;

    public Pitch(final int countOfPins) {
        validate(countOfPins);
        this.countOfPins = countOfPins;
        this.remains = MAXIMUM_COUNT_OF_PINS - countOfPins;
    }

    public static Pitch firstPitch(final int countOfPins) {
        if (countOfPins == MAXIMUM_COUNT_OF_PINS) {
            return new Strike();
        }
        return new Pitch(countOfPins);
    }

    private void validate(final int countOfPins) {
        if (countOfPins < MINIMUM_COUNT_OF_PINS || countOfPins > MAXIMUM_COUNT_OF_PINS) {
            throw new BusinessException(MINIMUM_COUNT_OF_PINS + "에서 " + MAXIMUM_COUNT_OF_PINS + "개의 핀을 쓰러트릴 수 있습니다.");
        }
    }

    public Pitch pitch(final int countOfPins) {
        if (!(this instanceof Strike) && this.countOfPins + countOfPins == MAXIMUM_COUNT_OF_PINS) {
            return new Spare(countOfPins);
        }
        if (!(this instanceof Strike) && countOfPins == MINIMUM_COUNT_OF_PINS) {
            return new Gutter();
        }
        if (this.remains == MINIMUM_COUNT_OF_PINS && countOfPins == MAXIMUM_COUNT_OF_PINS) {
            return new Strike();
        }
        return new Pitch(countOfPins);
    }

    public int intValue() {
        return countOfPins;
    }

    public String value() {
        return String.valueOf(countOfPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitch pitch = (Pitch) o;
        return countOfPins == pitch.countOfPins &&
                remains == pitch.remains;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPins, remains);
    }
}
