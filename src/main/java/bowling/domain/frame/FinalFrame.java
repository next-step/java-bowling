package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

    private LinkedList<State> states;
    private Score score;
    private static final int MIN_PITCH_COUNT = 2;
    private static final int MAX_PITCH_COUNT = 3;
    public static final String INVALID_END_PLAY = "더이상 진행 할 수 없습니다.";

    public FinalFrame() {
        this.states = new LinkedList<>();
        states.add(new Ready());
    }

    public void play(int count) {
        if (this.isEnd()) {
            throw new IllegalArgumentException(INVALID_END_PLAY);
        }

        if (states.getLast().isFinish()) {
            states.add(new Ready());
        }

        State state = states.getLast();
        states.removeLast();
        states.addLast(state.play(count));

        createScore();
    }

    private void createScore() {
        this.score = new Score(sumAllCount(), 0);
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

    private int sumAllCount() {
        return states.stream()
                .mapToInt(State::getTotalCount)
                .sum();
    }

    @Override
    public Frame next() {
        throw new IllegalArgumentException(INVALID_END_PLAY);
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
}
