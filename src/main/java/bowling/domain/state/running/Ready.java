package bowling.domain.state.running;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.domain.state.State;
import bowling.domain.state.finish.Strike;
import bowling.exception.state.StateCannotCalculateScoreException;

public class Ready extends Running {

    private static final String EMPTY_DESC = "";

    public Ready() {
    }

    @Override
    public State bowl(Pin pin) {
        if (pin.isStrike()) {
            return new Strike(pin);
        }
        return new FirstBowl(pin);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        throw new StateCannotCalculateScoreException();
    }

    @Override
    public String desc() {
        return EMPTY_DESC;
    }

}
