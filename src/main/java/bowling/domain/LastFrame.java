package bowling.domain;

import static java.util.stream.Collectors.joining;

import java.util.Collections;
import java.util.LinkedList;

public class LastFrame implements Frame {
    private static final String DELIMITER_OF_SCORE = "|";
    private static final int MIN_BOWL = 2;
    private static final int MAX_BOWL = 3;

    private final LinkedList<State> states;
    private int count;

    public LastFrame() {
        this.states = new LinkedList<>(Collections.singletonList(Ready.create()));
        this.count = 0;
    }

    @Override
    public Frame bowl(Pitching pitching) {
        count++;

        State lastState = states.getLast();

        if (lastState.isEnd()) {
            addNewStatus(pitching);
            return this;
        }
        changeLastStatus(pitching, lastState);
        return this;
    }

    private void addNewStatus(Pitching pitching) {
        State ready = Ready.create();
        states.add(ready.bowl(pitching));
    }

    private void changeLastStatus(Pitching pitching, State lastState) {
        states.removeLast();
        states.add(lastState.bowl(pitching));
    }

    @Override
    public boolean isEnd() {
        if (count == MAX_BOWL) {
            return true;
        }
        return count == MIN_BOWL && recentState() instanceof Miss;
    }

    @Override
    public int score() {
        if(!isEnd()) {
            return Score.INCALCULABLE_SCORE;
        }

        return states.stream()
                .map(State::score)
                .mapToInt(Score::getValue)
                .sum();
    }

    @Override
    public int calculationScore(Score before) {
        try {
            return calculationScore(before, 0);
        } catch (IllegalStateException | IndexOutOfBoundsException e) {
            return Score.INCALCULABLE_SCORE;
        }
    }

    private int calculationScore(Score before, int index) {
        State state = states.get(index);
        Score score = state.calculatorScore(before);
        if (score.isCalculatorScore()) {
            return score.getValue();
        }
        return calculationScore(score, index + 1);
    }

    private State recentState() {
        return states.getLast();
    }

    @Override
    public int getFrameNumber() {
        return FrameNumber.MAX_FRAME_NUMBER;
    }

    @Override
    public String toString() {
        return states.stream()
                .map(State::symbol)
                .collect(joining(DELIMITER_OF_SCORE));
    }
}
