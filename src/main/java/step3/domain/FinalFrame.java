package step3.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import step3.state.Ready;
import step3.state.State;

public class FinalFrame implements Frame {

    private int turn;
    private List<State> states = new ArrayList<>();
    private State state;

    public FinalFrame() {
        this.state = new Ready();
        turn = 0;
    }

    public Frame bowl(int fallenPins) {
        state = state.bowl(fallenPins);
        turn++;
        if (state.isFinish()) {
            states.add(state);
            state = new Ready();
        }
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

    @Override
    public Frame createFrame() {
        return this;
    }

    public boolean isGameEnd() {
        return turn == 3;
    }

    public State getState() {
        return state;
    }

    public List<State> getStates() {
        return states;
    }

    @Override
    public boolean isFinish() {
        return turn == 3;
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
