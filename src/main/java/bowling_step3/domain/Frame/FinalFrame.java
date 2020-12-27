package bowling_step3.domain.Frame;

import bowling_step3.domain.Score;
import bowling_step3.domain.state.*;
import bowling_step3.exception.PitchOverBoundException;

import java.util.LinkedList;

public class FinalFrame extends Frame {

    private final LinkedList<State> states = new LinkedList<>();

    public FinalFrame() {
        states.add(new Ready());
    }

    private boolean isStrikeOfSpare(State state) {
        return state instanceof Strike || state instanceof Spare;
    }

    private boolean hasBonusPitch() {
        return states.stream()
                .anyMatch(this::isStrikeOfSpare);
    }

    private int sumCountOfKnockDown() {
        return states.stream()
                .mapToInt(State::getPitchCount)
                .sum();
    }

    private void validate() {
        if (isFinish()) {
            throw new PitchOverBoundException();
        }
    }

    private boolean isLastFinish() {
        return states.getLast().isFinish();
    }

    private void statesAdd() {
        if (isLastFinish()) {
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

    private void createScore(Score score) {
        this.score = score.of(sumAll());
    }

    private boolean isSumPitchCount(int pitchCount) {
        return this.sumCountOfKnockDown() == pitchCount;
    }

    @Override
    public void pitch(int countOfKnockDown) {
        validate();
        statesAdd();
        pitchLast(countOfKnockDown);
        createScore(new Score());
    }

    @Override
    public boolean isFinish() {
        if (!hasBonusPitch() && isSumPitchCount(MIN_PITCH_COUNT)) {
            return true;
        }

        return isSumPitchCount(MAX_PITCH_COUNT);
    }

    @Override
    public Frame next() {
        throw new PitchOverBoundException();
    }

    public LinkedList<State> getStates() {
        return states;
    }

    @Override
    public int getScore() {
        return score.getScore();
    }

    @Override
    public boolean hasScore() {
        return isFinish();
    }

    @Override
    public void calculateScore(int index, int count) {

    }
}
