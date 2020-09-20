package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.exception.CannotCalculateException;

public class Ready extends Running {
    @Override
    public State bowl(Pin pin) {
        if (pin.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(pin);
    }

    @Override
    public boolean canBowlFinalFrame() {
        return true;
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        throw new CannotCalculateException();
    }

    @Override
    public String record() {
        return "";
    }
}
