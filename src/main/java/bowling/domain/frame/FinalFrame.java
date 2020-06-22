package bowling.domain.frame;

import bowling.domain.FrameResults;
import bowling.domain.exceptions.InvalidTryBowlException;
import bowling.domain.frameStatus.FinalFrameStatus;

import java.util.Objects;

public class FinalFrame {
    private static int TEN = 10;

    private final int index;
    private final FinalFrameStatus finalFrameStatus;
    private final NormalFrame prevFrame;

    FinalFrame(int index, FinalFrameStatus finalFrameStatus, NormalFrame ninthFrame) {
        this.index = index;
        this.finalFrameStatus = finalFrameStatus;
        this.prevFrame = ninthFrame;
    }

    FinalFrame(NormalFrame ninthFrame, FinalFrameStatus finalFrameStatus) {
        this(TEN, finalFrameStatus, ninthFrame);
    }

    public static FinalFrame firstBowl(int numberOfHitPin, NormalFrame ninthFrame) {
        return new FinalFrame(TEN, FinalFrameStatus.bowlFirst(numberOfHitPin), ninthFrame);
    }

    public FinalFrame bowl(int numberOfHitPin) {
        validateBowl();
        return new FinalFrame(TEN, this.finalFrameStatus.bowl(numberOfHitPin), prevFrame);
    }

    public boolean isCompleted() {
        return this.finalFrameStatus.isCompleted();
    }

    private void validateBowl() {
        if (isCompleted()) {
            throw new InvalidTryBowlException("종료된 프레임에는 투구할 수 없습니다.");
        }
    }

    public FrameResults calculateCurrentResults() {
        return this.finalFrameStatus.calculateCurrentResult();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return index == that.index &&
                Objects.equals(finalFrameStatus, that.finalFrameStatus) &&
                Objects.equals(prevFrame, that.prevFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, finalFrameStatus, prevFrame);
    }
}
