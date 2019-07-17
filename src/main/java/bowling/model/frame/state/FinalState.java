package bowling.model.frame.state;

import bowling.model.DownPin;
import bowling.model.frame.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static bowling.model.frame.state.Score.DEFAULT;
import static bowling.utils.Pretty.PARTITION_OF_SYMBOL;
import static java.util.stream.Collectors.joining;

public class FinalState implements State {

    private static final int MAX_NUMBER_OF_ROUND = 3;
    private static final int LAST_INDEX = 1;

    private List<State> states = new ArrayList<>();
    private int round;

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
        round++;
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

    List<State> getStates() {
        return Collections.unmodifiableList(states);
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
        int indexOfState = 0;
        if (states.size() == 1) {
            return DEFAULT;
        }
        while (prevScore.hasCountLeft()) {
            Score score = states.get(indexOfState).getScore();
            prevScore= prevScore.calculate(score);
        }
        return prevScore;
    }

    @Override
    public boolean isFinished() {
        return hasNotBonusStage() || MAX_NUMBER_OF_ROUND == round;
    }

    private boolean hasNotBonusStage() {
        return states.stream()
                .anyMatch(Miss.class::isInstance);
    }

    @Override
    public String printResult() {
        return states.stream()
                .map(State::printResult)
                .collect(joining(PARTITION_OF_SYMBOL));
    }
}
