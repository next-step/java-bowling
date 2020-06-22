package bowling.domain;

import bowling.domain.frameResult.FrameResult;
import bowling.domain.frameResult.NormalFrameResult;

public class FinalFrame {
    private FrameResult firstFrameResult;
    private FrameResult secondFrameResult;

    FinalFrame(FrameResult firstFrameResult, FrameResult secondFrameResult) {
        this.firstFrameResult = firstFrameResult;
        this.secondFrameResult = secondFrameResult;
    }

    public static FinalFrame firstBowl(int numberOfHitPin) {
        return new FinalFrame(FrameResultFactory.create(numberOfHitPin), null);
    }

    public void bowl(int numberOfHitPin) {
        if (this.secondFrameResult == null) {
            this.secondFrameResult = FrameResultFactory.create(numberOfHitPin);
        }
        if (!this.secondFrameResult.isCompleted()) {
            NormalFrameResult parsed = (NormalFrameResult) this.secondFrameResult;
            this.secondFrameResult = parsed.secondBowl(numberOfHitPin);
        }
    }

    public boolean isCompleted() {
        return (this.firstFrameResult.isCompleted() && this.secondFrameResult.isCompleted());
    }
}
