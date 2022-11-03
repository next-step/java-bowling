package bowling.domain.frame;

import bowling.domain.pin.FallenPin;
import bowling.domain.score.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class NormalFrame implements Frame {
    private final FrameNumber frameNumber;
    private State state;
    private Frame next;

    public NormalFrame(int frameNumber, State state) {
        this(new FrameNumber(frameNumber), state);
    }

    public NormalFrame(FrameNumber frameNumber, State state) {
        this.frameNumber = frameNumber;
        this.state = state;
    }

    public static NormalFrame init(int frameNumber) {
        return new NormalFrame(frameNumber, new Ready());
    }

    public static NormalFrame init(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber, new Ready());
    }

    @Override
    public Frame bowl(FallenPin fallenPin) {
        state = state.bowl(fallenPin);
        if (isFinished()) {
            next = nextFrame();
            return next;
        }

        return this;
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
    }

    @Override
    public List<State> getStates() {
        return List.of(state);
    }

    @Override
    public boolean isReady() {
        return state instanceof Ready;
    }

    @Override
    public Score getScore() {
        Score score = state.getScore();
        if (score.canCalculate()) {
            return score;
        }

        return next.addScore(score);
    }

    @Override
    public Score addScore(Score previousScore) {
        Score score = state.addScore(previousScore);
        if (score.canCalculate()) {
            return score;
        }

        return Optional.ofNullable(next)
                .map(nextFrame -> nextFrame.addScore(score))
                .orElse(null);
    }

    private Frame nextFrame() {
        FrameNumber nextFrameNumber = frameNumber.increase();
        if (nextFrameNumber.isFinal()) {
            return FinalFrame.init();
        }

        return NormalFrame.init(nextFrameNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalFrame)) return false;

        NormalFrame that = (NormalFrame) o;

        if (!Objects.equals(frameNumber, that.frameNumber)) return false;
        if (!Objects.equals(state, that.state)) return false;
        return Objects.equals(next, that.next);
    }

    @Override
    public int hashCode() {
        int result = frameNumber.hashCode();
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (next != null ? next.hashCode() : 0);
        return result;
    }
}
