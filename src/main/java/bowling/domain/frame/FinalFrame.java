package bowling.domain.frame;

import bowling.domain.pin.FallenPin;
import bowling.domain.score.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {
    private static final int BONUS_INCLUDED_TOTAL_TRIES = 3;

    private final List<State> states;

    FinalFrame(List<State> states) {
        this.states = states;
    }

    public static FinalFrame init() {
        List<State> result = new ArrayList<>();
        result.add(new Ready());
        return new FinalFrame(result);
    }

    @Override
    public Frame bowl(FallenPin fallenPin) {
        if (lastState().isFinished()) {
            states.add(new Ready().bowl(fallenPin));
            return this;
        }

        states.set(lastIndex(), lastState().bowl(fallenPin));
        return this;
    }

    @Override
    public boolean isFinished() {
        if (firstState() instanceof Strike || firstState() instanceof Spare) {
            return totalTries() == BONUS_INCLUDED_TOTAL_TRIES;
        }

        return firstState().isFinished();
    }

    @Override
    public List<State> getStates() {
        return states;
    }

    @Override
    public boolean isReady() {
        return lastState() instanceof Ready;
    }

    @Override
    public Score getScore() {
        Score score = firstState().getScore();
        if (score.canCalculate()) {
            return score;
        }

        return addScoreAfterFirstState(score);
    }

    @Override
    public Score addScore(Score previousScore) {
        Score score = firstState().addScore(previousScore);
        if (score.canCalculate()) {
            return score;
        }

        return addScoreAfterFirstState(score);
    }

    private Score addScoreAfterFirstState(Score previousScore) {
        if (previousScore.canCalculate()) {
            return previousScore;
        }

        Score score = previousScore;
        for (int i = 1; i < states.size(); i++) {
            score = states.get(i).addScore(score);
            if (score.canCalculate()) {
                return score;
            }
        }

        return null;
    }

    private int totalTries() {
        return states.stream()
                .mapToInt(State::tries)
                .sum();
    }

    private State firstState() {
        return states.get(0);
    }

    private State lastState() {
        return states.get(lastIndex());
    }

    private int lastIndex() {
        return states.size() - 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinalFrame)) return false;

        FinalFrame that = (FinalFrame) o;

        return Objects.equals(states, that.states);
    }

    @Override
    public int hashCode() {
        return states != null ? states.hashCode() : 0;
    }
}
