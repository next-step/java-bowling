package bowling.domain;

import bowling.exception.BusinessException;

import java.util.Objects;

public class Pitch {
    public static final int MINIMUM_COUNT_OF_PINS = 0;
    public static final int MAXIMUM_COUNT_OF_PINS = 10;
    public static final String SEPARATOR = "|";

    private final int countOfPins;
    private final Status status;

    public Pitch(final int countOfPins, final Status status) {
        validate(countOfPins);
        this.countOfPins = countOfPins;
        this.status = status;
    }

    public static Pitch firstPitch(final int countOfPins) {
        return new Pitch(countOfPins, Status.first(countOfPins));
    }

    private void validate(final int countOfPins) {
        if (countOfPins < MINIMUM_COUNT_OF_PINS || countOfPins > MAXIMUM_COUNT_OF_PINS) {
            throw new BusinessException(MINIMUM_COUNT_OF_PINS + "에서 " + MAXIMUM_COUNT_OF_PINS + "개의 핀을 쓰러트릴 수 있습니다.");
        }
    }

    public boolean isStrike() {
        return status.equals(Status.STRIKE);
    }

    public Pitch pitch(final int countOfPins) {
        return new Pitch(countOfPins, Status.second(this, countOfPins));
    }

    public int intValue() {
        return countOfPins;
    }

    public String value() {
        if (status.equals(Status.NUMBER)) {
            return String.valueOf(countOfPins);
        }
        return status.symbol();
    }

    public boolean isStatus(Status status) {
        return this.status.equals(status);
    }

    public Status status() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitch pitch = (Pitch) o;
        return countOfPins == pitch.countOfPins &&
                status == pitch.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPins, status);
    }

}
