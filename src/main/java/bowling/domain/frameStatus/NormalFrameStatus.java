package bowling.domain.frameStatus;

import bowling.domain.NormalBonusScore;
import bowling.domain.FrameResult;
import bowling.domain.FrameResults;
import bowling.domain.NumberOfHitPin;
import bowling.domain.exceptions.CannotCalculateFrameResultException;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class NormalFrameStatus implements FrameStatus {
    private static final NumberOfHitPin STRIKE = new NumberOfHitPin(10);

    private final NumberOfHitPin firstNumberOfHitPin;
    private final NumberOfHitPin secondNumberOfHitPin;

    public NormalFrameStatus(NumberOfHitPin firstNumberOfHitPin, NumberOfHitPin secondNumberOfHitPin) {
        this.firstNumberOfHitPin = firstNumberOfHitPin;
        this.secondNumberOfHitPin = secondNumberOfHitPin;
    }

    public static NormalFrameStatus bowlFirst(int numberOfHitPin) {
        return new NormalFrameStatus(new NumberOfHitPin(numberOfHitPin), null);
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

    @Override
    public NormalBonusScore calculateBonusScore() {
        return NormalBonusScore.of(this.firstNumberOfHitPin, this.secondNumberOfHitPin);
    }

    @Override
    public FrameResults calculateCurrentResult() {
        if (this.firstNumberOfHitPin != null && this.secondNumberOfHitPin == null) {
            return new FrameResults(Collections.singletonList(FrameResult.find(firstNumberOfHitPin)));
        }
        if (this.firstNumberOfHitPin != null) {
            return new FrameResults(
                    Arrays.asList(
                            FrameResult.find(firstNumberOfHitPin),
                            FrameResult.find(firstNumberOfHitPin, secondNumberOfHitPin))
            );
        }
        throw new CannotCalculateFrameResultException("첫 투구를 진행하지 않은 상태에서 결과를 계산할 수 없습니다.");
    }

    @Override
    public NormalFrameStatus bowl(int numberOfHitPin) {
        return new NormalFrameStatus(firstNumberOfHitPin, new NumberOfHitPin(numberOfHitPin));
    }

    @Override
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
        NormalFrameStatus that = (NormalFrameStatus) o;
        return Objects.equals(firstNumberOfHitPin, that.firstNumberOfHitPin) &&
                Objects.equals(secondNumberOfHitPin, that.secondNumberOfHitPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNumberOfHitPin, secondNumberOfHitPin);
    }

    @Override
    public String toString() {
        return "NormalFrameStatus{" +
                "firstNumberOfHitPin=" + firstNumberOfHitPin +
                ", secondNumberOfHitPin=" + secondNumberOfHitPin +
                '}';
    }
}
