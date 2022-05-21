package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.score.Score;

public class Ready implements State {

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
    public Score score() {
        return null;
    }

    @Override
    public Score calculateScore(Score beforeScore) {
        return Score.unavailable();
    }

}
