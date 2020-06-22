package bowling.domain.frame;

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

    public static FinalFrame firstBowl(int numberOfHitPin, NormalFrame ninthFrame) {
        return new FinalFrame(TEN, FinalFrameStatus.bowlFirst(numberOfHitPin), ninthFrame);
    }

    public FinalFrame bowl(int numberOfHitPin) {
        return new FinalFrame(TEN, this.finalFrameStatus.bowl(numberOfHitPin), prevFrame);
    }

    public boolean isCompleted() {
        return this.finalFrameStatus.isCompleted();
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
