package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.domain.state.*;

public class NormalFrame implements Frame {
    private FrameState frameState;

    public NormalFrame(FrameState frameState) {
        validateFrameState(frameState);
        this.frameState = frameState;
    }

    private void validateFrameState(FrameState frameState) {
        if (frameState == null) {
            throw new IllegalArgumentException("프레임 상태는 null 일 수 없습니다.");
        }
    }

    public static NormalFrame initialize() {
        return new NormalFrame(new BeforeProgress());
    }

    @Override
    public void bowl(Pins hitPins) {
        this.frameState = frameState.bowl(hitPins);
    }

    @Override
    public boolean isFrameEnd() {
        return frameState.isEnd();
    }

    @Override
    public boolean isFinalFrame() {
        return false;
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

        return frameState.equals(that.frameState);
    }

    @Override
    public int hashCode() {
        return frameState.hashCode();
    }
}