package bowling.step3.domain.state;

import java.util.Objects;

public class Pins {
    private static final String ONE_PITCH_EXCEPTION = "한번의 투구는 0보다 작거나 10보다 큰 값이 올 수 없습니다.";
    private static final String STRIKE = "X";
    private static final String GUTTER = "-";
    private static final int STRIKE_POINT = 10;
    private static final int SPARE_POINT = 10;
    private static final int GUTTER_POINT = 0;
    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private int countOfPins;

    public Pins(int countOfPins) {
        validatePins(countOfPins);
        this.countOfPins = countOfPins;
    }

    private void validatePins(int countOfPins) {
        if(countOfPins < MIN_PINS || countOfPins > MAX_PINS){
            throw new IllegalArgumentException(ONE_PITCH_EXCEPTION);
        }
    }

    public boolean isInvalidatePins(Pins pins) {
        return countOfPins + pins.countOfPins > MAX_PINS;
    }

    public boolean isStrike() {
        return countOfPins == STRIKE_POINT;
    }

    public boolean isSpare(Pins pins) {
        return countOfPins + pins.countOfPins == SPARE_POINT;
    }

    public boolean isGutter() {
        return countOfPins == GUTTER_POINT;
    }

    public int getCountOfPins() {
        return countOfPins;
    }

    public String display() {
        if(isStrike()){
            return STRIKE;
        }
        if(isGutter()){
            return GUTTER;
        }
        return String.valueOf(countOfPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins = (Pins) o;
        return countOfPins == pins.countOfPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPins);
    }
}
