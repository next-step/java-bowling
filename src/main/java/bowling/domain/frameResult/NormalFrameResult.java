package bowling.domain.frameResult;

import bowling.domain.NumberOfHitPin;

import java.util.Objects;

public class NormalFrameResult {
    private final NumberOfHitPin firstNumberOfHitPin;
    private final NumberOfHitPin secondNumberOfHitPin;

    NormalFrameResult(NumberOfHitPin firstNumberOfHitPin, NumberOfHitPin secondNumberOfHitPin) {
        this.firstNumberOfHitPin = firstNumberOfHitPin;
        this.secondNumberOfHitPin = secondNumberOfHitPin;
    }

    public static NormalFrameResult firstBowl(int numberOfHitPin) {
        return new NormalFrameResult(new NumberOfHitPin(numberOfHitPin), null);
    }

    public NormalFrameResult secondBowl(int numberOfHitPin) {
        return new NormalFrameResult(firstNumberOfHitPin, new NumberOfHitPin(numberOfHitPin));
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
