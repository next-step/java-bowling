package bowling_step3.domain.Frame;

import bowling_step3.domain.Frame.Frame;
import bowling_step3.domain.Score;
import bowling_step3.domain.state.Ready;
import bowling_step3.domain.state.Spare;
import bowling_step3.domain.state.State;
import bowling_step3.domain.state.Strike;
import bowling_step3.exception.PitchOverBoundException;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {

    private static final int DEFAULT = 0;

    private static final int MIN_PITCH_COUNT = 2;

    private static final int MAX_PITCH_COUNT = 3;

    private LinkedList<State> states;

    public FinalFrame() {
        this.states = new LinkedList<>();
        states.add(new Ready());
    }

    private boolean hasBonusPitch() {
        return states.stream()
                .anyMatch(state -> state instanceof Strike || state instanceof Spare);
    }

    private int sumCountOfKnockDown() {
        return states.stream()
                .mapToInt(State::getPitchCount)
                .sum();
    }

    private void validate() {
        if (this.isFinish()) {
            throw new PitchOverBoundException();
        }
    }

    private void statesAdd() {
        if (states.getLast().isFinish()) {
            states.add(new Ready());
        }
    }

    private void pitchLast(int count) {
        State state = states.getLast();
        states.removeLast();
        states.addLast(state.pitch(count));
    }

    private int sumAll() {
        return states.stream()
                .mapToInt(State::getTotalCount)
                .sum();
    }

    private void createScore() {
        this.score = new Score(sumAll(), DEFAULT);
    }

    @Override
    public void pitch(int countOfKnockDown) {
        validate();
        statesAdd();
        pitchLast(countOfKnockDown);
        createScore();
    }

    @Override
    public boolean isFinish() {
        if (states.isEmpty()) {
            return false;
        }

        if (!hasBonusPitch() && this.sumCountOfKnockDown() == MIN_PITCH_COUNT) {
            return true;
        }

        return this.sumCountOfKnockDown() == MAX_PITCH_COUNT;
    }

    @Override
    public Frame next() {
        throw new PitchOverBoundException();
    }

    @Override
    public String getKnockDownExpression() {
        return states.stream()
                .map(State::toString)
                .collect(Collectors.joining("|"));
    }

    @Override
    public int getScore() {
        return this.score.getScore();
    }

    @Override
    public boolean hasScore() {
        return isFinish();
    }

    @Deprecated
    public void calculateScore(int index, int count) {

    }
}
