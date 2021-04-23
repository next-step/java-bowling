package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

    private static final int MIN_PITCH_COUNT = 2;
    private static final int MAX_PITCH_COUNT = 3;

    private LinkedList<State> states;
    private Score score;
    private State state;

    public FinalFrame() {
        states = new LinkedList<>();
        states.add(new Ready());
    }

    @Override
    public void bowl(int count) {
        if (isEnd()) {
            throw new IllegalArgumentException("프레임은 종료되었습니다");
        }

        if (states.getLast().isFinish()) {
            states.add(new Ready());
        }

        State state = states.getLast();
        states.removeLast();
        states.addLast(state.bowl(count));
        score = new Score(sumCount(), 0);
    }

    @Override
    public boolean isEnd() {
        if (states.isEmpty()) {
            return false;
        }

        if (!hasBonusPitch() && sumBowlCount() == MIN_PITCH_COUNT) {
            return true;
        }

        return sumBowlCount() == MAX_PITCH_COUNT;
    }

    private boolean hasBonusPitch() {
        return states.stream()
                .anyMatch(state -> state instanceof Strike || state instanceof Spare);
    }

    private int sumBowlCount() {
        return states.stream()
                .mapToInt(State::getPitchCount)
                .sum();
    }

    private int sumCount() {
        return states.stream()
                .mapToInt(State::getTotalCount)
                .sum();
    }

    @Override
    public Frame next() {
        throw new IllegalStateException("마지막 프레임입니다.");
    }

    @Override
    public String getFallenPins() {
        return states.stream()
                .map(State::toString)
                .collect(Collectors.joining("|"));
    }

    @Override
    public int getScore() {
        return score.getScore();
    }

    @Override
    public void calculateScore(int index, int count) {

    }

    @Override
    public boolean hasScore() {
        return isEnd();
    }


}
