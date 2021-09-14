package step3.domain;

import java.util.Objects;
import step3.exceptions.CannotCalculateExceptions;
import step3.state.Ready;
import step3.state.State;

public class NormalFrame implements Frame {

    private Frame next;
    private State state;
    private int no;

    public NormalFrame(int frameNum) {
        this.state = new Ready();
        this.no = frameNum;
    }

    public Frame bowl(int fallenPins) {
        state = state.bowl(fallenPins);
        return this;
    }

    public Frame createFrame() {
        if (no + 1 == 10) {
            next = new FinalFrame();
            return next;
        }
        next = new NormalFrame(no + 1);
        return next;
    }

    public Score getScore() {
        Score score = state.score();
        if (score.canCalculateScore()) {
            return score;
        }
        if (next == null) {
            throw new CannotCalculateExceptions();
        }
        return next.calculateAdditionalScore(score);
    }

    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = state.calculateAdditionalScore(beforeScore);
        if (score.canCalculateScore()) {
            return score;
        }
        return next.calculateAdditionalScore(score);
    }

    @Override
    public int number() {
        return no;
    }

    @Override
    public String getSymbol() {
        return state.symbol();
    }

    public boolean isGameEnd() {
        return false;
    }

    public State getState() {
        return state;
    }

    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NormalFrame that = (NormalFrame) o;
        return no == that.no && Objects.equals(next, that.next) && Objects
            .equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(next, state, no);
    }
}
