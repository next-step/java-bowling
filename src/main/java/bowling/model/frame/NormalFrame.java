package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.frame.state.None;
import bowling.model.frame.state.Score;

import java.util.Objects;


public class NormalFrame extends Frame {

    private final Frame next;
    private State state;

    private NormalFrame(FrameNumber frameNumber) {
        super(frameNumber);
        this.next = nextFrame();
        this.state = None.getInstance();
    }

    private Frame nextFrame() {
        FrameNumber nextFrameNumber = nextFrameNumber();
        return nextFrameNumber.isFinalNumber() ? FinalFrame.of()
                : NormalFrame.of(nextFrameNumber);
    }

    static NormalFrame ofFirst() {
        return new NormalFrame(FrameNumber.NUMBER_OF_START_FRAME);
    }

    static Frame of(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }

    @Override
    public Frame bowl(Pins downPins) {
        state = state.bowl(downPins);
        if (state.isFinished()) {
            return next;
        }
        return this;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public String printResult() {
        return state.printResult() + SEPARATOR_OF_FRAME + next.printResult();
    }

    @Override
    public Score getScore() {
        Score score = state.getScore();
        if (score.isCompleted()) {
            return score;
        }
        return next.calculate(score);
    }

    @Override
    Score calculate(Score score) {
        if (!state.isFinished()) {
            return score;
        }
        if (score.isCompleted()) {
            return score;
        }
        Score calculated = state.calculate(score);
        if (calculated.isCompleted()) {
            return calculated;
        }
        return next.calculate(calculated);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(next, that.next) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(next, state);
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "next=" + next +
                ", state=" + state +
                '}';
    }
}