package bowling.domain.state.running;

import bowling.domain.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.finish.Strike;

public class Ready extends Running {

    private static final String EMPTY = "";

    private Ready() {
    }

    public static State create() {
        return new Ready();
    }

    @Override
    public State pitch(Pins pins) {
        if (pins.isStrike()) {
            return Strike.create();
        }

        return FirstBowl.create(pins);
    }

    @Override
    public boolean isFrameEnd() {
        return false;
    }

    @Override
    public String getSymbol() {
        return EMPTY;
    }

    @Override
    public Score calculateScore(Score beforeScore) {
        return Score.unavailable();
    }

}
