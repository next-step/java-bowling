package step3.domain;

import java.util.Objects;
import step3.state.Ready;
import step3.state.State;

public class FinalFrame implements Frame {
    private State state;

    public FinalFrame() {
        this.state = new Ready();
    }

    public Frame bowl(int fallenPins) {
        state = state.bowl(fallenPins);

        return this;
    }

    public Score getScore() {
        Score score = state.score();
        if (score.canCalculateScore()) {
            return score;
        }

        return score;
    }

    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = state.calculateAdditionalScore(beforeScore);
        if (score.canCalculateScore()) {
            return score;
        }
        return score;
    }

    @Override
    public int number() {
        return 10;
    }

    @Override
    public String getSymbol() {
        return state.symbol();
    }

    public boolean isGameEnd() {
        return state.isFinish();
    }

    public State getState() {
        return state;
    }

    @Override
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
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
