package bowling.domain.frameResult;

import bowling.domain.FrameStatuses;
import bowling.domain.NumberOfHitPin;

public class FinalFrameResult implements FrameResult {
    private NumberOfHitPin firstNumberOfHitPin;

    public FinalFrameResult(NumberOfHitPin numberOfHitPin) {
        this.firstNumberOfHitPin = numberOfHitPin;
    }

    public static FinalFrameResult firstBowl(int numberOfHitPin) {
        return new FinalFrameResult(new NumberOfHitPin(numberOfHitPin));
    }

    @Override
    public boolean isStrikeResult() {
        return false;
    }

    @Override
    public boolean isCompleted() {
        // TODO: 투구를 몇번했는지에 따라 다른 결과 반환하도록 구현 필요
        return false;
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public FrameStatuses calculateCurrentStatus() {
        // TODO
        return null;
    }
}
