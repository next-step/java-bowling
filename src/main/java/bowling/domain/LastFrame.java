package bowling.domain;

import bowling.domain.state.Ready;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LastFrame implements Frame {
    public static final int LAST_FRAME_NUMBER = 10;

    private LinkedList<State> states = new LinkedList<>();

    public LastFrame() {
        states.add(new Ready());
    }

    public static Frame from() {
        return new LastFrame();
    }

    @Override
    public LastFrame hit(int count) {

        State currentState = states.getLast();

        if (currentState.isFinish()) {
            states.add(State.last(currentState, count));
            return this;
        }

        states.removeLast();
        states.add(currentState.roll(count));
        return this;
    }

    @Override
    public int getNumber() {
        return LAST_FRAME_NUMBER;
    }

    @Override
    public List<String> toResults() {
        return states.stream()
                .flatMap(state -> state.toValues().stream())
                .collect(Collectors.toList());
    }

    @Override
    public boolean isFinish() {
        State lastState = states.getLast();

        if (!lastState.isFinish()) {
            return false;
        }

        return !getCurrentScore().canNextSum();
    }

    private Score getCurrentScore() {
        State state = states.getFirst();
        Score score = state.getScore();

        for (int index = 1; index < states.size(); index++) {
            score = states.get(index).sumScore(score);
        }

        return score;
    }

    @Override
    public Score getScore() {
        return Score.ofMiss(
                states.stream()
                        .mapToInt(state -> state.getScore().toInt())
                        .sum()
        );
    }

    @Override
    public Score additionalScore(Score beforeScore) {
        for (State state : states) {
            beforeScore = state.sumScore(beforeScore);
        }
        return beforeScore;
    }

    @Override
    public String toString() {
        return "LastFrame{" +
                "states=" + states +
                '}';
    }
}
