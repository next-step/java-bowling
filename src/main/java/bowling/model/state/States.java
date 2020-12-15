package bowling.model.state;

import bowling.model.state.finishedState.FinishedState;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class States {
    private static final String CHANGE_LAST_TO_BONUS_FRAME_ERROR = "Strike 혹은 Spare만 보너스 프레임이 가능합니다.";

    private static final String DELIMITER = "|";
    private final LinkedList<State> states;

    public States() {
        states = new LinkedList<>();
        states.add(new Start());
    }

    public State bowling(int fallenPins) {
        State bowlingResult = last().bowling(fallenPins);
        return add(bowlingResult);
    }

    private State add(State state) {
        states.add(state);
        return state;
    }

    public State last() {
        return states.getLast();
    }

    public boolean isFinished() {
        return last().isFinished();
    }

    public boolean isMaxScore() {
        return last().isMaxScore();
    }

    public void changeLastToBonusFrame() {
        State lastState = last();

        if (!lastState.isFinished() || !lastState.isMaxScore()) {
            throw new IllegalArgumentException(CHANGE_LAST_TO_BONUS_FRAME_ERROR);
        }

        states.set(states.size() - 1, BonusOpen.from((FinishedState) last()));
    }

    public boolean isStart() {
        return states.size() == 1;
    }

    @Override
    public String toString() {
        if (states.size() == 1) {
            return "";
        }

        return states.subList(1, states.size()).stream()
                .map(State::toString)
                .collect(Collectors.joining(DELIMITER));
    }
}
