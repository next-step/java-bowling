package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Objects;

public class NormalFrame implements Frame {
    private final int number;
    private Frame next;
    private State state;

    public NormalFrame(int number, State state) {
        this.number = number;
        this.state = state;
    }

    public NormalFrame(int number) {
        this.number = number;
        this.state = new Ready();
    }

    public static NormalFrame first() {
        return new NormalFrame(1);
    }

    public NormalFrame next() {
        this.next = new NormalFrame(number + 1);
        return (NormalFrame) next;
    }

    public LastFrame last() {
        this.next = new LastFrame();
        return (LastFrame) next;
    }

    @Override
    public int number() {
        return number;
    }

    @Override
    public Frame bowl(int pins) {
        this.state = state.bowl(pins);
        if (state.finish()) {
            return next;
        }
        return this;
    }

    @Override
    public boolean canCalculateCurrentScore() {
        if (!state.finish()) {
            return false;
        }
        Score score = state.score();
        if (score.canCalculate()) {
            return true;
        }
        return next.canCalculatePrefixSumScore(score);
    }

    @Override
    public boolean canCalculatePrefixSumScore(Score beforeScore) {
        if (!state.canCalculate(beforeScore)) {
            return false;
        }

        if (beforeScore.canCalculate()) {
            return true;
        }

        return next.canCalculatePrefixSumScore(beforeScore);
    }

    @Override
    public Score score() {
        Score score = state.score();
        if (score.canCalculate()) {
            return score;
        }
        return next.prefixSumScore(score);
    }

    @Override
    public Score prefixSumScore(Score beforeScore) {
        Score score = state.sumBeforeScore(beforeScore);
        if (score.canCalculate()) {
            return score;
        }
        return next.prefixSumScore(score);
    }

    @Override
    public String toString() {
        return state.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame frame = (NormalFrame) o;
        return number == frame.number && Objects.equals(state, frame.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, state);
    }
}
