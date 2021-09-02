package bowling.domain.frame;

import bowling.domain.pins.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BonusFrame implements Frame {

    private static final int LAST_COUNT = 3;

    private final List<State> states = new ArrayList<>();

    private BonusFrame(State state) {
        states.add(state);
    }

    public static BonusFrame of(State state) {
        return new BonusFrame(state);
    }

    @Override
    public int getFrameNumber() {
        return FinalFrame.FINAL_FRAME_NUMBER;
    }

    @Override
    public Frame bowl(Pins pins) {
        State state = getLastState();

        if (state.isFinish()) {
            states.add(Ready.of().bowl(pins));
            return this;
        }

        states.remove(states.size() - 1);
        states.add(state.bowl(pins));

        return this;
    }

    @Override
    public boolean isFinish() {
        return states.size() == LAST_COUNT || isFirstSpare();
    }

    @Override
    public String getState() {
        return states.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    @Override
    public Score getScore() {
        Score score = states.get(0).getScore();
        for (int i = 1; i < states.size(); i++) {
            State state = states.get(i);
            score = state.calculateAdditionalScore(score);
        }

        return score;
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        for (int i = 1; i < states.size(); i++) {
            State state = states.get(i);
            score = state.calculateAdditionalScore(score);
        }

        return score;
    }

    private State getLastState() {
        return states.get(states.size() - 1);
    }

    private boolean isFirstSpare() {
        return states.size() == 2 && states.get(0) instanceof Spare;
    }
}
