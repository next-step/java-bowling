package bowling.domain;

import bowling.domain.frameResult.FrameResult;
import bowling.domain.frameResult.NormalFrameResult;
import bowling.domain.exceptions.CannotBowlException;
import bowling.domain.exceptions.CannotDoNextFrameException;
import bowling.domain.exceptions.ParseNormalFrameException;

import java.util.Objects;

public class NormalFrame implements Frame {
    private FrameResult frameResult;
    private NormalFrame nextNormalFrame;

    NormalFrame(FrameResult frameResult, NormalFrame nextNormalFrame) {
        this.frameResult = frameResult;
        this.nextNormalFrame = nextNormalFrame;
    }

    public static NormalFrame bowlFirst(int numberOfHitPin) {
        return new NormalFrame(FrameResultFactory.create(numberOfHitPin), null);
    }

    public NormalFrame bowlSecond(int secondNumberOfHitPin) {
        validateSecondBowl();

        NormalFrameResult afterFrameResult = parseToNormalFrameResult(this.frameResult)
                .secondBowl(secondNumberOfHitPin);

        return new NormalFrame(afterFrameResult, null);
    }

    public NormalFrame next(int numberOfHitPin) {
        validateNextFrame();

        NormalFrame nextNormalFrame = NormalFrame.bowlFirst(numberOfHitPin);
        this.nextNormalFrame = nextNormalFrame;

        return nextNormalFrame;
    }

    private void validateNextFrame() {
        if (!this.frameResult.isCompleted()) {
            throw new CannotDoNextFrameException("현재 프레임을 마무리하기 전에는 다음 프레임으로 넘어갈 수 없습니다.");
        }
    }

    private void validateSecondBowl() {
        if (this.frameResult.isCompleted()) {
            throw new CannotBowlException("점수 계산이 완료된 Frame에서는 추가로 공을 굴릴 수 없습니다.");
        }
    }

    private NormalFrameResult parseToNormalFrameResult(FrameResult frameResult) {
        if (frameResult.isStrikeResult()) {
            throw new ParseNormalFrameException("Strike cannot convert to NormalFrameResult");
        }
        return (NormalFrameResult) frameResult;
    }

    public NormalFrame toNext() {
        return this.nextNormalFrame;
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public FrameStatuses calculateCurrentStatus() {
        return this.frameResult.calculateCurrentStatus();
    }

    @Override
    public boolean isCompleted() {
        return this.frameResult.isCompleted();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame normalFrame = (NormalFrame) o;
        return Objects.equals(frameResult, normalFrame.frameResult) &&
                Objects.equals(nextNormalFrame, normalFrame.nextNormalFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameResult, nextNormalFrame);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "frameResult=" + frameResult +
                ", nextFrame=" + nextNormalFrame +
                '}';
    }
}
