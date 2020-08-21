package bowling.domian.frame;

import bowling.domian.state.running.Ready;
import bowling.domian.state.State;

import java.util.Objects;

public class NormalFrame implements Frame {
    private final int frameNumber;

    private State state;

    private NormalFrame(int frameNumber) {
        this.frameNumber = frameNumber;
        this.state = new Ready();
    }

    public static NormalFrame firstFrame() {
        return new NormalFrame(1);
    }

    @Override
    public int getFrameNumber() {
        return frameNumber;
    }

    public Frame bowl(int falledPinsCount) {
        State nextState = state.bowl(falledPinsCount);

        if (nextState.isFinished()) {
            return getNextFrame();
        }

        this.state = nextState;
        return this;
    }

    private Frame getNextFrame() {
        if (frameNumber < 9) {
            return new NormalFrame(frameNumber + 1);
        }

        return new FinalFrame();
    }

    @Override
    public boolean isGameEnd() {
        return false;
    }

    @Override
    public NormalFrameResult getFrameResult() {
        return NormalFrameResult.get(this.state);
    }

    public void addResult(Board board) {
        board.addResult(getFrameResult(), frameNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return frameNumber == that.frameNumber &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, state);
    }
}
