package bowling.domain;

import bowling.domain.state.InitState;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

import java.util.Objects;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-17 00:53
 */
public class NormalFrame implements Frame {
    private State state;
    private final FrameNumber frameNumber;
    private Frame nextFrame;

    public NormalFrame() {
        this.frameNumber = FrameNumber.init();
        this.state = InitState.of();
    }

    public NormalFrame(FrameNumber frameNumber) {
        this.state = InitState.of();
        this.frameNumber = frameNumber;
    }

    @Override
    public Frame bowl(int fallCount) {
        if (isGameOver()) {
            FinalFrame finalFrame = new FinalFrame(frameNumber.next());
            nextFrame = finalFrame;
            return finalFrame.bowl(fallCount);
        }
        if (state.isOver(false)) {
            return nextFrame(fallCount);
        }
        state = state.update(Point.of(fallCount), false);
        return this;
    }

    @Override
    public boolean isGameOver() {
        return frameNumber.isNormalFrameOver() && state.isOver(false);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public int getNumber() {
        return frameNumber.getFrameNumber();
    }

    @Override
    public Score getScore() {
        return updateScore(state.stateScore());
    }

    @Override
    public Score updateScore(Score source) {
        if (!isCalculable(source)) {
            return source;
        }
        Score nextFrameScore = nextFrame.getState().stateScore();
        Score calculateScore = source.calculate(nextFrameScore);

        return nextFrame.updateScore(calculateScore);
    }

    private boolean isCalculable(Score source) {
        if (source.remainCalculate() && nextFrame != null) {
            return true;
        }
        return false;
    }

    private NormalFrame nextFrame(int fallCount) {
        NormalFrame newFrame = new NormalFrame(frameNumber.next());
        nextFrame = newFrame;
        newFrame.bowl(fallCount);

        return newFrame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(state, that.state) &&
                Objects.equals(frameNumber, that.frameNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, frameNumber);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "state=" + state +
                ", frameNumber=" + frameNumber +
                '}';
    }
}
