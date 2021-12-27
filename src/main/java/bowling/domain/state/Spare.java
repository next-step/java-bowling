package bowling.domain.state;

import bowling.domain.value.Pins;

import java.util.Objects;

public class Spare extends Finished{

    private static final String SPARE_MARK = "/";

    private final Pins firstPin;
    private final Pins secondPin;

    public Spare(Pins firstPin, Pins secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public static boolean isSpare(State state) {
        return true;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public String getMark() {
        return checkGutter(firstPin) + DELIMITER + SPARE_MARK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Spare spare = (Spare) o;
        return Objects.equals(firstPin, spare.firstPin) && Objects.equals(secondPin, spare.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }

}
