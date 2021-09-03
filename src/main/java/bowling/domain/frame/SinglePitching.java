package bowling.domain.frame;

import java.util.Objects;

public class SinglePitching {

    public static final int MIN_FALLEN_PIN = 0;
    public static final int MAX_FALLEN_PIN = 10;

    private final int fallenPin;

    public SinglePitching(int fallenPin) {
        validate(fallenPin);
        this.fallenPin = fallenPin;
    }

    private void validate(int fallenPin) {
        if (fallenPin < MIN_FALLEN_PIN || fallenPin > MAX_FALLEN_PIN) {
            throw new SinglePitchingException(fallenPin);
        }
    }

    public boolean isStrike() {
        return this.fallenPin == MAX_FALLEN_PIN;
    }

    public boolean isSpare(SinglePitching firstPitching) {
        return !firstPitching.isStrike() && sumIsMax(firstPitching);
    }

    private boolean sumIsMax(SinglePitching singlePitching) {
        return sum(singlePitching) == MAX_FALLEN_PIN;
    }

    public int sum(SinglePitching singlePitching) {
        return this.fallenPin + singlePitching.fallenPin();
    }

    public int fallenPin() {
        return fallenPin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SinglePitching that = (SinglePitching) o;
        return fallenPin == that.fallenPin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPin);
    }
}
