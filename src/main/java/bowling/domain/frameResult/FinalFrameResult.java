package bowling.domain.frameResult;

import bowling.domain.NumberOfHitPin;

import java.util.Objects;

public class FinalFrameResult implements FrameResult {
    private static final NumberOfHitPin STRIKE = new NumberOfHitPin(10);

    private final NumberOfHitPin firstNumberOfHitPin;
    private final NumberOfHitPin secondNumberOfHitPin;
    private final NumberOfHitPin thirdNumberOfHitPin;

    FinalFrameResult(
            NumberOfHitPin firstNumberOfHitPin, NumberOfHitPin secondNumberOfHitPin, NumberOfHitPin thirdNumberOfHitPin
    ) {
        this.firstNumberOfHitPin = firstNumberOfHitPin;
        this.secondNumberOfHitPin = secondNumberOfHitPin;
        this.thirdNumberOfHitPin = thirdNumberOfHitPin;
    }

    public static FinalFrameResult bowlFirst(int numberOfHitPin) {
        return new FinalFrameResult(new NumberOfHitPin(numberOfHitPin), null, null);
    }

    @Override
    public FinalFrameResult bowl(int numberOfHitPin) {
        if (this.secondNumberOfHitPin != null) {
            return new FinalFrameResult(firstNumberOfHitPin, secondNumberOfHitPin, new NumberOfHitPin(numberOfHitPin));
        }
        return new FinalFrameResult(firstNumberOfHitPin, new NumberOfHitPin(numberOfHitPin), null);
    }

    @Override
    public boolean isCompleted() {
        if (this.firstNumberOfHitPin.equals(STRIKE)) {
            return this.secondNumberOfHitPin != null && this.thirdNumberOfHitPin != null;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrameResult that = (FinalFrameResult) o;
        return Objects.equals(firstNumberOfHitPin, that.firstNumberOfHitPin) &&
                Objects.equals(secondNumberOfHitPin, that.secondNumberOfHitPin) &&
                Objects.equals(thirdNumberOfHitPin, that.thirdNumberOfHitPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNumberOfHitPin, secondNumberOfHitPin, thirdNumberOfHitPin);
    }
}
