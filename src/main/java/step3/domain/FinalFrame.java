package step3.domain;

import step3.domain.state.Ready;
import step3.domain.state.State;

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

    public boolean isGameEnd() {
        return false;
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean isFinish() {
        return state.isFinish();
    }
}
