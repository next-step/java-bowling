package domain.frame;

import domain.Pins;
import domain.bowling.LastFrameSet;
import domain.score.Score;
import domain.state.Ready;
import domain.state.State;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LastFrame implements Frame {

    private final static int VALID_STATE_LIMIT = 3;

    private List<State> states;
    private LastFrameSet bowling;

    public LastFrame() {
        this.states = new ArrayList<>();
        this.bowling = new LastFrameSet();
    }

    @Override
    public Frame setKnockedDownPins(Pins knockedDown) {
        bowling = (LastFrameSet) bowling.bowl(knockedDown);
        State state = bowling.getFrameState();
        states.add(state);
        return this;
    }

    @Override
    public Score getScore() {
        State lastScore = getLastState();
        if (lastScore.isClosed()) {
            return Score.of(sumCompletedState(states));
        }
        return Score.of(sumCompletedStateWithLastScore(lastScore));
    }

    private int sumCompletedState(List<State> states) {
        return states.stream()
                .filter(State::isClosed)
                .map(State::getScore)
                .mapToInt(Score::getValue)
                .sum();
    }

    @Override
    public Score getBonusScore(Score beforeScore) {

        if(states.size() >= VALID_STATE_LIMIT) {
            states = states.stream()
                    .filter(State::isClosed)
                    .collect(Collectors.toList());
        }
        for (State state : states) {
            beforeScore = state.calculateBonusScore(beforeScore);
            if (emptyBonus(beforeScore)) {
                break;
            }
        }
        return beforeScore;
    }

    private boolean emptyBonus(Score beforeScore) {
        return !beforeScore.hasBonus();
    }

    @Override
    public List<State> getState() {
        return states;
    }

    @Override
    public boolean isClosed() {
        return bowling.isClosed();
    }

    private State getLastState() {
        return states.isEmpty() ? new Ready() : states.get(states.size() - 1);
    }

    private int sumCompletedStateWithLastScore(State lastScore) {
        List<State> collect = states.stream()
                .filter(State::isClosed)
                .collect(Collectors.toList());

        collect.add(lastScore);

        return collect.stream()
                .map(State::getScore)
                .mapToInt(Score::getValue)
                .sum();
    }
}
