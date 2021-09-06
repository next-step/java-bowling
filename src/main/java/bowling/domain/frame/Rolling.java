package bowling.domain.frame;

import java.util.Objects;

public class Rolling {

    public static final int MIN_FALLEN_PIN = 0;
    public static final int MAX_FALLEN_PIN = 10;

    private final int fallenPin;

    public Rolling(int fallenPin) {
        validate(fallenPin);
        this.fallenPin = fallenPin;
    }

    private void validate(int fallenPin) {
        if (fallenPin < MIN_FALLEN_PIN || fallenPin > MAX_FALLEN_PIN) {
            throw new RollingException(fallenPin);
        }
    }

    public boolean isStrike() {
        return this.fallenPin == MAX_FALLEN_PIN;
    }

    public boolean isSpare(Rolling firstRolling) {
        return !firstRolling.isStrike() && sumIsMax(firstRolling);
    }

    private boolean sumIsMax(Rolling rolling) {
        return plusFallenPin(rolling) == MAX_FALLEN_PIN;
    }

    public int plusFallenPin(Rolling rolling) {
        return this.fallenPin + rolling.fallenPin();
    }

    public int fallenPin() {
        return fallenPin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rolling that = (Rolling) o;
        return fallenPin == that.fallenPin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPin);
    }
}
