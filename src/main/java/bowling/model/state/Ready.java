package bowling.model.state;

import bowling.model.Pin;
import bowling.model.Score;

public class Ready extends Running {

    @Override
    public State bowl(Pin pin) {
        if (pin.isClearAll()) {
            return new Strike();
        }
        return new First(pin);
    }

    @Override
    public Score addBonusScore(Score score) {
        return score;
    }

    @Override
    public String toString() {
        return " ";
    }
}
