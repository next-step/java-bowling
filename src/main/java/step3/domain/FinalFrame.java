package step3.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import step3.state.FinalReady;
import step3.state.State;

public class FinalFrame extends Frame {

    private int turn;
    private List<State> states = new ArrayList<>();
    private State state;

    public FinalFrame() {
        this.state = new FinalReady();
        turn = 0;
    }

    @Override
    public Frame bowl(int fallenPins) {
        state = state.bowl(fallenPins);
        turn++;
        return this;
    }

    public Score getScore() {
        return state.score();
    }

    public int getLastFrameResult() {
        return states.stream()
            .map(state -> state.score().getScore())
            .reduce(0, Integer::sum);
    }

    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = beforeScore;

        for (State state : states) {
            score = state.calculateAdditionalScore(score);
            if (score.canCalculateScore()) {
                return score;
            }
        }
        return score;
    }


    @Override
    public int number() {
        return 10;
    }

    @Override
    public String getSymbol() {
        return states.stream()
            .map(State::symbol)
            .collect(Collectors.joining("|"));
    }

    @Override
    public Frame createFrame() {
        states.add(state);
        state = new FinalReady();

        return this;
    }

    public boolean isGameEnd() {
        return turn == 3 || (turn == 2 && states.get(1).score().getScore() != 10);
    }

    public State getState() {
        return state;
    }

    public List<State> getStates() {
        return states;
    }

    @Override
    public boolean isFinish() {
        return true;
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
