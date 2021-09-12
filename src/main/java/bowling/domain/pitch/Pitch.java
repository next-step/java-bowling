package bowling.domain.pitch;

import bowling.exception.BusinessException;

public class Pitch {
    public static final int MINIMUM_COUNT_OF_PINS = 0;
    public static final int MAXIMUM_COUNT_OF_PINS = 10;

    private final int countOfPins;

    public Pitch(final int countOfPins) {
        validate(countOfPins);
        this.countOfPins = countOfPins;
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
        if (this.countOfPins + countOfPins == MAXIMUM_COUNT_OF_PINS) {
            return new Spare(countOfPins);
        }
        if (countOfPins == MINIMUM_COUNT_OF_PINS) {
            return new Gutter();
        }
        return new Pitch(countOfPins);
    }

    public String value() {
        return String.valueOf(countOfPins);
    }
}
