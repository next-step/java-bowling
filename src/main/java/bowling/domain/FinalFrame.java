package bowling.domain;

import bowling.domain.exceptions.ParseNormalFrameException;
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
        if (firstFrameResult.isStrikeResult()) {
            this.secondFrameResult = bowlAfterFirstStrike(numberOfHitPin);
        }
    }

    private FrameResult bowlAfterFirstStrike(int numberOfHitPin) {
        if (this.secondFrameResult == null) {
            return this.secondFrameResult = FrameResultFactory.create(numberOfHitPin);
        }
        return this.secondFrameResult = parseToNormal(this.secondFrameResult).secondBowl(numberOfHitPin);
    }

    public boolean isCompleted() {
        return (this.firstFrameResult.isCompleted() && this.secondFrameResult.isCompleted());
    }

    private NormalFrameResult parseToNormal(FrameResult frameResult) {
        if (!frameResult.isStrikeResult()) {
            return (NormalFrameResult) frameResult;
        }
        throw new ParseNormalFrameException("StrikeFrameResult는 NormalFrameResult로 변경할 수 없습니다.");
    }
}
