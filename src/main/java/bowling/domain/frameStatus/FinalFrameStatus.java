package bowling.domain.frameStatus;

import bowling.domain.FrameResult;
import bowling.domain.FrameResults;
import bowling.domain.NumberOfHitPin;
import bowling.domain.exceptions.CannotCalculateFrameResultException;
import bowling.domain.exceptions.InvalidTryBowlException;

import java.util.Arrays;
import java.util.Collections;
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
    public FrameResults calculateCurrentResult() {
        if (this.firstNumberOfHitPin != null && this.secondNumberOfHitPin == null && this.thirdNumberOfHitPin == null) {
            return calculateFirstProgress();
        }
        if (this.firstNumberOfHitPin != null && this.secondNumberOfHitPin != null && this.thirdNumberOfHitPin == null) {
            return calculateSecondProgress();
        }
        if (this.firstNumberOfHitPin != null && this.secondNumberOfHitPin != null) {
            return calculateCompleted();
        }
        throw new CannotCalculateFrameResultException("결과를 계산할 수 없는 상태입니다.");
    }

    private FrameResults calculateFirstProgress() {
        return new FrameResults(Collections.singletonList(FrameResult.find(firstNumberOfHitPin)));
    }

    private FrameResults calculateSecondProgress() {
        if (this.firstNumberOfHitPin.equals(STRIKE)) {
            return new FrameResults(
                    Arrays.asList(
                            FrameResult.find(this.firstNumberOfHitPin), FrameResult.find(this.secondNumberOfHitPin)));
        }
        return new FrameResults(
                Arrays.asList(
                        FrameResult.find(this.firstNumberOfHitPin),
                        FrameResult.find(this.firstNumberOfHitPin, this.secondNumberOfHitPin)
                ));
    }

    private FrameResults calculateCompleted() {
        if (this.firstNumberOfHitPin.equals(STRIKE) && this.secondNumberOfHitPin.equals(STRIKE)) {
            return new FrameResults(
                    Arrays.asList(
                            FrameResult.find(this.firstNumberOfHitPin),
                            FrameResult.find(this.secondNumberOfHitPin),
                            FrameResult.find(this.thirdNumberOfHitPin)
                    ));
        }
        if (this.firstNumberOfHitPin.equals(STRIKE) && !this.secondNumberOfHitPin.equals(STRIKE)) {
            return new FrameResults(
                    Arrays.asList(
                            FrameResult.find(this.firstNumberOfHitPin),
                            FrameResult.find(this.secondNumberOfHitPin),
                            FrameResult.find(this.secondNumberOfHitPin, this.thirdNumberOfHitPin)
                    ));
        }
        return new FrameResults(
                Arrays.asList(
                        FrameResult.find(this.firstNumberOfHitPin),
                        FrameResult.find(this.firstNumberOfHitPin, this.secondNumberOfHitPin),
                        FrameResult.find(this.thirdNumberOfHitPin)
                ));
    }

    private boolean isMiss() {
        return this.firstNumberOfHitPin.plus(this.secondNumberOfHitPin).compareTo(STRIKE) < 0;
    }

    private boolean isSpare() {
        return this.firstNumberOfHitPin.plus(this.secondNumberOfHitPin).equals(STRIKE);
    }

    @Override
    public boolean isCompleted() {
        if (this.firstNumberOfHitPin.equals(STRIKE)) {
            return this.secondNumberOfHitPin != null && this.thirdNumberOfHitPin != null;
        }
        if (this.secondNumberOfHitPin != null && isSpare()) {
            return this.thirdNumberOfHitPin != null;
        }
        return this.secondNumberOfHitPin != null && isMiss();
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
