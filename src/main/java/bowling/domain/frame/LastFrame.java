package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.running.Ready;
import bowling.domain.state.State;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LastFrame implements Frame {

    private static final String VERTICAL_BAR = "|";

    private static final int MIN_OF_PITCH = 2;
    private static final int MAX_OF_PITCH = 3;

    private final LinkedList<State> states;

    private int pitchCount = 0;

    private LastFrame() {
        this.states = new LinkedList<>(List.of(Ready.create()));
    }

    public static Frame create() {
        return new LastFrame();
    }

    @Override
    public boolean isFrameEnd() {
        if (pitchCount == MAX_OF_PITCH) {
            return true;
        }
        return pitchCount == MIN_OF_PITCH && states.getLast() instanceof Miss;
    }

    @Override
    public void pitch(Pins pins) {
        pitchCount++;

        State lastState = states.getLast();
        if (lastState.isFrameEnd()) {
            addNewStatus(pins);
            return;
        }

        updateLastStatus(pins, lastState);
    }

    private void addNewStatus(Pins pins) {
        State ready = Ready.create();
        states.add(ready.pitch(pins));
    }

    private void updateLastStatus(Pins pins, State lastState) {
        states.removeLast();
        states.add(lastState.pitch(pins));
    }

    @Override
    public String getSymbol() {
        return states.stream()
                .map(State::getSymbol)
                .collect(Collectors.joining(VERTICAL_BAR));
    }

    @Override
    public Score score() {
        int firstTrialIndex = 0;
        Score score = states.get(firstTrialIndex).score();

        int secondTrialIndex = 1;
        for (int index = secondTrialIndex; index < states.size(); index++) {
            State state = states.get(index);
            score = state.calculateScore(score);
        }

        return score;
    }

    @Override
    public final Score calculateAdditionalScore(final Score beforeScore) {
        if (beforeScore.finishCalculation()) {
            return beforeScore;
        }

        Score score = beforeScore;
        for (State state : states) {
            score = state.calculateScore(score);
        }

        return score;
    }

}
