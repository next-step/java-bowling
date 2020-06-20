package bowling.domain;

import bowling.domain.exceptions.CannotBowlException;
import bowling.domain.exceptions.CannotDoNextFrameException;
import bowling.domain.exceptions.ParseNormalFrameException;

import java.util.Objects;

public class Frame {
    private FrameResult frameResult;
    private Frame nextFrame;

    Frame(FrameResult frameResult, Frame nextFrame) {
        this.frameResult = frameResult;
        this.nextFrame = nextFrame;
    }

    public static Frame bowlFirst(int numberOfHitPin) {
        return new Frame(FrameResultFactory.create(numberOfHitPin), null);
    }

    public Frame bowlSecond(int secondNumberOfHitPin) {
        validateSecondBowl();

        NormalFrameResult afterFrameResult = parseToNormalFrameResult(this.frameResult)
                .secondBowl(secondNumberOfHitPin);

        return new Frame(afterFrameResult, null);
    }

    public Frame next(int numberOfHitPin) {
        validateNextFrame();

        Frame nextFrame = Frame.bowlFirst(numberOfHitPin);
        this.nextFrame = nextFrame;

        return nextFrame;
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

    public boolean isFinal() {
        return this.frameResult.isFinalFrame();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(frameResult, frame.frameResult) &&
                Objects.equals(nextFrame, frame.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameResult, nextFrame);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "frameResult=" + frameResult +
                ", nextFrame=" + nextFrame +
                '}';
    }
}
