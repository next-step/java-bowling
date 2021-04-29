package step4.domain;

import step4.domain.state.Ready;
import step4.domain.state.Spare;
import step4.domain.state.State;
import step4.domain.state.Strike;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {
    private static final int FINAL_INDEX = 9;
    private static final int START = 0;
    private static final int TRY_NUMBER_MAX = 3;
    private static final String GAME_OVER = "게임이 완료되어 더 이상 공을 던질 수 없습니다.";

    private LinkedList<State> states = new LinkedList<>();
    private int tryNumber;

    public FinalFrame() {
        states.add(new Ready());
        tryNumber = START;
    }

    @Override
    public Frame throwBowl(String pinCount) {
        if (isFinished()) {
            throw new IllegalStateException(GAME_OVER);
        }

        tryNumber += 1;
        int knockedDownPinCount = Integer.parseInt(pinCount);
        State currentState = states.getLast();

        if (currentState.isFinished()) {
            states.add(new Ready().bowl(knockedDownPinCount));
            return this;
        }

        states.removeLast();
        states.add(currentState.bowl(knockedDownPinCount));
        return this;
    }

    @Override
    public boolean isFinished() {
        return tryNumber == TRY_NUMBER_MAX
                || (tryNumber == TRY_NUMBER_MAX - 1 && !isThirdBowlAvailable());
    }

    private boolean isThirdBowlAvailable() {
        return states.stream()
                .anyMatch(this::isStrikeOrSpare);
    }

    private boolean isStrikeOrSpare(State state) {
        return state instanceof Strike
                || state instanceof Spare;
    }

    @Override
    public List<String> states() {
        return states.stream()
                .map(State::marks)
                .collect(Collectors.toList());
    }

    @Override
    public Frame next() {
        return this;
    }

    @Override
    public int index() {
        return FINAL_INDEX;
    }

    @Override
    public Score score() {
        if (!isFinished()) {
            return Score.unCountableScore();
        }

        Score score = states.getFirst().score();

        for (int i = 1; i < states.size(); i++) {
            State state = states.get(i);
            score = state.addScore(score);
        }

        return score;
    }

    @Override
    public Score add(Score score) {
        if ((states.getFirst() instanceof Ready)) {
            return Score.unCountableScore();
        }

        for (int i = 0; i < score.leftOpportunity(); i++) {
            score = states.get(i).addScore(score);
        }

        return score;
    }
}
