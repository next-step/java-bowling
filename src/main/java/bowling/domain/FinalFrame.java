package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;
import bowling.exception.GameOverException;

import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

    private static final int MIN_PITCH_COUNT = 2;
    private static final int MAX_PITCH_COUNT = 3;

    private final Pins pins;
    private LinkedList<State> states;
    private Score score;

    public FinalFrame() {
        this.pins = new Pins();

        this.states = new LinkedList<>();
        states.add(new Ready());
    }

    public void pitch(int count) {
        if (this.isEnd()) {
            throw new GameOverException();
        }

        if (states.getLast().isFinish()) {
            states.add(new Ready());
        }

        State state = states.getLast();
        states.removeLast();
        states.addLast(state.pitch(count));

        pins.pitch(count);
        createScore();
    }

    private void createScore() {
        this.score = new Score(pins.getSum(), 0);
    }

    public boolean isEnd() {
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
        return isEnd();
    }

    @Override
    public void calculateScore(int index, int count) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(pins, that.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
