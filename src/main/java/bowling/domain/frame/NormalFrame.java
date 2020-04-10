package bowling.domain.frame;

import bowling.domain.state.Pin;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Objects;

public class NormalFrame implements Frame {
    private static final int FINAL_FRAME_NUMBER = 10;

    private int frameNumber;
    private State state;

    public NormalFrame(int frameNumber) {
        this.frameNumber = frameNumber;
        this.state = new Ready();
    }

    @Override
    public Frame bowl(Pin fallenPins) {
        state = state.bowl(fallenPins);
        if (state.isFinish()) {
            return createFrame();
        }
        return this;
    }

    private Frame createFrame() {
        if (frameNumber + 1 == FINAL_FRAME_NUMBER) {
            return new FinalFrame();
        }
        return new NormalFrame(frameNumber + 1);
    }

    @Override
    public Score calculateByBeforeScore(Score beforeScore) {
        return state.calculateByBeforeScore(beforeScore);
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public void updateScore(Score score) {
        state.updateScore(score);
    }

    @Override
    public Result getFrameResult() {
        if (!state.isFinish()) {
            return new Result(state.display(), -1);
        }
        if (Objects.nonNull(state.getScore()) && state.getScore().isCalculation()) {
            return new Result(state.display(), state.getScore().getScore());
        }
        return new Result(state.display(), -1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame frame = (NormalFrame) o;
        return frameNumber == frame.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }

}
