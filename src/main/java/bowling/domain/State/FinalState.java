package bowling.domain.State;

import bowling.domain.score.FinishedScore;
import bowling.domain.score.Score;
import bowling.domain.score.UnDefinedScore;

import java.util.ArrayList;
import java.util.List;

public class FinalState implements State {

    private static final int MAX_TRY_COUNT = 3;

    private final List<State> states = new ArrayList<>();

    private int tryCount;

    public FinalState() {
        states.add(new Ready());
    }

    public FinalState(List<State> states, int tryCount) {
        this.states.addAll(states);
        this.tryCount = tryCount;
    }

    @Override
    public State newState(PinCount pinCount) {
        if (isClosed()) {
            throw new IllegalStateException("이미 종료된 상태입니다.");
        }
        tryCount++;
        changeLastState(pinCount);
        return new FinalState(states, tryCount);
    }

    private void changeLastState(PinCount pinCount) {
        State lastState = lastState();
        if (lastState.isClosed()) {
            Ready ready = new Ready();
            states.add(ready);
            lastState = ready;
        }
        states.set(states.size() - 1, lastState.newState(pinCount));
    }

    @Override
    public boolean isClosed() {
        return isTryAll() || isFirstStateIsMiss();
    }

    @Override
    public String stateInString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(states.get(0).stateInString());
        for (int i = 1; i <= states.size() - 1; i++) {
            stringBuilder.append(Miss.SEPARATOR_SYMBOL)
                    .append(states.get(i).stateInString());
        }
        return stringBuilder.toString();
    }

    @Override
    public Score score() {
        int totalScoreInInt = states.stream()
                .reduce(0,
                        (accumulated, state) -> state.score().sumCurrentScore(accumulated),
                        Integer::sum);

        if (isClosed()) {
            return new FinishedScore(totalScoreInInt);
        }
        return new UnDefinedScore(totalScoreInInt);
    }

    @Override
    public Score calculatedScore(Score scoreToCalculate) {
        Score finalScore = scoreToCalculate;
        for (State state : states) {
            finalScore = nextCalculatedScore(state, finalScore);
        }
        return finalScore;
    }

    private Score nextCalculatedScore(State state, Score finalScore) {
        if (!finalScore.isNecessaryToCalculateMore()) {
            return finalScore;
        }
        return state.calculatedScore(finalScore);
    }

    private boolean isFirstStateIsMiss() {
        State firstState = states.get(0);
        return firstState instanceof Miss;
    }

    private boolean isTryAll() {
        return tryCount == MAX_TRY_COUNT;
    }

    private State lastState() {
        return states.get(states.size() - 1);
    }


}
