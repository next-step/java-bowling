package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;
import bowling.exception.GameOverException;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

    private static final int MIN_PITCH_COUNT = 2;
    private static final int MAX_PITCH_COUNT = 3;

    private LinkedList<State> states;
    private Score score;

    public FinalFrame() {
        this.states = new LinkedList<>();
        states.add(new Ready());
    }

    public void pitch(int count) {
        if (this.isFinish()) {
            throw new GameOverException();
        }

        if (states.getLast().isFinish()) {
            states.add(new Ready());
        }

        State state = states.getLast();
        states.removeLast();
        states.addLast(state.pitch(count));

        createScore();
    }

    private void createScore() {
        this.score = new Score(sumAllCount(), 0);
    }

    public boolean isFinish() {
        if (states.isEmpty()) {
            return false;
        }

        if (!hasBonusPitch() && this.sumAllPitchCount() == MIN_PITCH_COUNT) {
            return true;
        }

        return this.sumAllPitchCount() == MAX_PITCH_COUNT;
    }

    private boolean hasBonusPitch() {
        return states.stream()
                .anyMatch(state -> state instanceof Strike || state instanceof Spare);
    }

    private int sumAllPitchCount() {
        return states.stream()
                .mapToInt(State::getPitchCount)
                .sum();
    }

    private int sumAllCount() {
        return states.stream()
                .mapToInt(State::getTotalCount)
                .sum();
    }

    @Override
    public Frame next() {
        throw new GameOverException();
    }

    @Override
    public String getFallenPins() {
        return states.stream()
                .map(State::toString)
                .collect(Collectors.joining("|"));
    }

    @Override
    public int getScore() {
        return this.score.getScore();
    }

    public boolean hasScore() {
        return isFinish();
    }

    @Override
    public void calculateScore(int index, int count) {

    }
}
