package domain.state.open;

import domain.Pins;
import domain.Score;
import domain.state.State;
import domain.state.closed.Strike;

public class StandBy extends Open {
    private static final String STAND_BY_SYMBOL = " ";

    @Override
    public State update(Pins fallenPins) {
        if (fallenPins.isStrike()) {
            return new Strike();
        }
        return new Hit(fallenPins);
    }

    @Override
    public String printState() {
        return STAND_BY_SYMBOL;
    }

    @Override
    public Score updateScore(Score score) {
        return Score.ofUnfinished();
    }

    @Override
    public Score getScore() {
        return Score.ofDefault();
    }
}
