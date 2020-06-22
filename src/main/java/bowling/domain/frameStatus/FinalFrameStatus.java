package bowling.domain.frameStatus;

import bowling.domain.NumberOfHitPin;
import bowling.domain.exceptions.InvalidTryBowlException;

import java.util.Objects;

public class FinalFrameStatus implements FrameStatus {
    private static final NumberOfHitPin STRIKE = new NumberOfHitPin(10);

    private final NumberOfHitPin firstNumberOfHitPin;
    private final NumberOfHitPin secondNumberOfHitPin;
    private final NumberOfHitPin thirdNumberOfHitPin;

    FinalFrameStatus(
            NumberOfHitPin firstNumberOfHitPin, NumberOfHitPin secondNumberOfHitPin, NumberOfHitPin thirdNumberOfHitPin
    ) {
        this.firstNumberOfHitPin = firstNumberOfHitPin;
        this.secondNumberOfHitPin = secondNumberOfHitPin;
        this.thirdNumberOfHitPin = thirdNumberOfHitPin;
    }

    public static FinalFrameStatus bowlFirst(int numberOfHitPin) {
        return new FinalFrameStatus(new NumberOfHitPin(numberOfHitPin), null, null);
    }

    @Override
    public FinalFrameStatus bowl(int numberOfHitPin) {
        if (this.firstNumberOfHitPin == null) {
            throw new InvalidTryBowlException("초구를 던지지 않은 상태에서 시도할 수 없습니다.");
        }
        if (this.secondNumberOfHitPin != null) {
            return new FinalFrameStatus(firstNumberOfHitPin, secondNumberOfHitPin, new NumberOfHitPin(numberOfHitPin));
        }
        return new FinalFrameStatus(firstNumberOfHitPin, new NumberOfHitPin(numberOfHitPin), null);
    }

    @Override
    public boolean isCompleted() {
        if (this.firstNumberOfHitPin.equals(STRIKE)) {
            return this.secondNumberOfHitPin != null && this.thirdNumberOfHitPin != null;
        }
        return this.secondNumberOfHitPin != null && isMiss();
    }

    private boolean isMiss() {
        return this.firstNumberOfHitPin.plus(this.secondNumberOfHitPin).compareTo(STRIKE) < 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrameStatus that = (FinalFrameStatus) o;
        return Objects.equals(firstNumberOfHitPin, that.firstNumberOfHitPin) &&
                Objects.equals(secondNumberOfHitPin, that.secondNumberOfHitPin) &&
                Objects.equals(thirdNumberOfHitPin, that.thirdNumberOfHitPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNumberOfHitPin, secondNumberOfHitPin, thirdNumberOfHitPin);
    }
}
