package bowling.model.frame.state;

import bowling.model.Count;
import bowling.model.DownPin;
import bowling.model.frame.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static bowling.model.Count.COUNT_THIRD;
import static bowling.utils.Pretty.PARTITION_OF_SYMBOL;
import static java.util.stream.Collectors.joining;

public class FinalState implements State {

    private static final Count MAX_OF_ROUND = COUNT_THIRD;
    private static final int LAST_INDEX = 1;

    private List<State> states = new ArrayList<>();
    private Count round = Count.COUNT_ZERO;

    private FinalState() {
        ready();
    }

    public static State valueOf() {
        return new FinalState();
    }

    private void ready() {
        states.add(Ready.getInstance());
    }

    @Override
    public State bowl(DownPin downPin) {
        round = round.increase();
        if (getCurrentState().isFinished()) {
            ready();
        }

        State state = getCurrentState().bowl(downPin);

        states.set(getLastStateIndex(), state);
        return state;
    }

    private State getCurrentState() {
        return states.get(getLastStateIndex());
    }

    private int getLastStateIndex() {
        return states.size() - LAST_INDEX;
    }

    @Override
    public Score getScore() {
        int totalScore = states.stream()
                .map(State::getScore)
                .mapToInt(Score::getScore)
                .sum();
        return Score.of(totalScore);
    }

    @Override
    public Score calculate(Score prevScore) {
        for (State state : states) {
            prevScore = prevScore.calculate(state.getScore());
        }

        return prevScore;
    }

    @Override
    public String printResult() {
        return states.stream()
                .map(State::printResult)
                .collect(joining(PARTITION_OF_SYMBOL));
    }

    @Override
    public boolean isFinished() {
        return hasNotBonusStage() || MAX_OF_ROUND.isMatch(round);
    }

    private boolean hasNotBonusStage() {
        return states.stream()
                .anyMatch(Miss.class::isInstance);
    }

    List<State> getStates() {
        return Collections.unmodifiableList(states);
    }
}
