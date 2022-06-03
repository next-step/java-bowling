package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.domain.state.*;

public class NormalFrame implements Frame {
    private static final int START_FRAME_NUMBER = 0;

    private FrameState frameState;
    private final FrameNumber frameNumber;

    public NormalFrame(FrameState frameState, FrameNumber frameNumber) {
        validateFrameStateAndFrameNumber(frameState, frameNumber);
        this.frameState = frameState;
        this.frameNumber = frameNumber;
    }

    private void validateFrameStateAndFrameNumber(FrameState frameState, FrameNumber frameNumber) {
        if (frameState == null) {
            throw new IllegalArgumentException("프레임 상태는 null 일 수 없습니다.");
        }
        if (frameNumber == null) {
            throw new IllegalArgumentException("프레임 넘버는 null 일 수 없습니다.");
        }
    }

    public static Frame create(FrameNumber frameNumber) {
        return new NormalFrame(new BeforeProgress(), frameNumber);
    }

    public static NormalFrame initialize() {
        return new NormalFrame(new BeforeProgress(), new FrameNumber(START_FRAME_NUMBER));
    }

    @Override
    public Frame bowl(Pins hitPins) {
        this.frameState = frameState.bowl(hitPins);
        if (isFrameEnd()) {
            return nextFrame();
        }
        return this;
    }

    private Frame nextFrame() {
        if (frameNumber.nextFrameNumber().isLast()) {
            return FinalFrame.initialize();
        }
        return create(frameNumber.nextFrameNumber());
    }

    @Override
    public boolean isFrameEnd() {
        return frameState.isEnd();
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    public FrameNumber frameNumber() {
        return frameNumber;
    }

    @Override
    public String symbol() {
        return frameState.symbol();
    }

    @Override
    public Score score() {
        return frameState.score();
    }

    @Override
    public Score calculateAdditionalScore(Score previousScore) {
        return frameState.calculateAdditionalScore(previousScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NormalFrame that = (NormalFrame) o;

        if (!frameState.equals(that.frameState)) return false;
        return frameNumber.equals(that.frameNumber);
    }

    @Override
    public int hashCode() {
        int result = frameState.hashCode();
        result = 31 * result + frameNumber.hashCode();
        return result;
    }
}