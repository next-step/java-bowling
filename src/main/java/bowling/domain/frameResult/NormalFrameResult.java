package bowling.domain.frameResult;

import bowling.domain.NumberOfHitPin;

import java.util.Objects;

public class NormalFrameResult {
    private static final NumberOfHitPin STRIKE = new NumberOfHitPin(10);

    private final NumberOfHitPin firstNumberOfHitPin;
    private final NumberOfHitPin secondNumberOfHitPin;

    NormalFrameResult(NumberOfHitPin firstNumberOfHitPin, NumberOfHitPin secondNumberOfHitPin) {
        this.firstNumberOfHitPin = firstNumberOfHitPin;
        this.secondNumberOfHitPin = secondNumberOfHitPin;
    }

    public static NormalFrameResult bowlFirst(int numberOfHitPin) {
        return new NormalFrameResult(new NumberOfHitPin(numberOfHitPin), null);
    }

    public NormalFrameResult bowlSecond(int numberOfHitPin) {
        return new NormalFrameResult(firstNumberOfHitPin, new NumberOfHitPin(numberOfHitPin));
    }

    public boolean isStrike() {
        return this.firstNumberOfHitPin.equals(STRIKE);
    }

    public boolean isSpare() {
        if (this.firstNumberOfHitPin != null && this.secondNumberOfHitPin != null) {
            return this.firstNumberOfHitPin.plus(this.secondNumberOfHitPin).equals(STRIKE);
        }
        return false;
    }

    public boolean isCompleted() {
        if (this.firstNumberOfHitPin != null && isStrike()) {
            return true;
        }
        return this.firstNumberOfHitPin != null && this.secondNumberOfHitPin != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrameResult that = (NormalFrameResult) o;
        return Objects.equals(firstNumberOfHitPin, that.firstNumberOfHitPin) &&
                Objects.equals(secondNumberOfHitPin, that.secondNumberOfHitPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNumberOfHitPin, secondNumberOfHitPin);
    }
}
