package bowling.model.state;

import bowling.model.Score;
import bowling.model.state.bonusState.BonusOpen;
import bowling.model.state.bonusState.BonusStart;
import bowling.model.state.finishedState.FinishedState;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class States {
    private static final String CHANGE_LAST_TO_BONUS_FRAME_ERROR = "Strike 혹은 Spare만 보너스 프레임이 가능합니다.";
    private static final String DELIMITER = "|";

    private final LinkedList<State> states = Stream.of(new Start())
            .collect(Collectors.toCollection(LinkedList::new));

    public State bowling(int fallenPins) {
        State bowlingResult = last().bowling(fallenPins);

        states.add(bowlingResult);

        return bowlingResult;
    }

    public void add(State state) {
        states.add(state);
    }

    public State last() {
        return states.getLast();
    }

    public boolean isBeforeMaxScore() {
        int size = states.size();
        return size != 1 && states.get(size - 2).isMaxScore();
    }

    public boolean isFinished() {
        return last().isFinished();
    }

    public boolean isMaxScore() {
        return last().isMaxScore();
    }

    public void changeLastToBonusOpen() {
        validBonus();
        states.set(states.size() - 1, BonusOpen.from((FinishedState) last()));
    }

    public void changeLastToBonusStart() {
        validBonus();
        states.set(states.size() - 1, BonusStart.from((FinishedState) last()));
    }

    private void validBonus(){
        State lastState = last();

        if (!lastState.isFinished() || !lastState.isMaxScore()) {
            throw new IllegalArgumentException(CHANGE_LAST_TO_BONUS_FRAME_ERROR);
        }
    }

    public Score calculate(Score score) {
        Score accumulator = score;

        for (int i = 1; i < states.size() || accumulator.canCalculate(); i++) {
            accumulator = accumulator.add(states.get(i).score());
        }

        return accumulator;
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
