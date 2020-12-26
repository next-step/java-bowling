package bowling_step3.domain.Frame;

import bowling_step3.domain.Score;
import bowling_step3.domain.state.Ready;
import bowling_step3.domain.state.Spare;
import bowling_step3.domain.state.State;
import bowling_step3.domain.state.Strike;
import bowling_step3.exception.PitchOverBoundException;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {

    private static final String VERTICAL = "|";

    private LinkedList<State> states = new LinkedList<>();

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
        if (this.isFinish()) {
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

    private void createScore() {
        Score score = new Score();
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
        createScore();
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

    @Override
    public String getKnockDownExpression() {
        return states.stream()
                .map(State::toString)
                .collect(Collectors.joining(VERTICAL));
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
