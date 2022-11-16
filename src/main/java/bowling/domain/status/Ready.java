package bowling.domain.status;

import bowling.domain.Pin;
import bowling.domain.Score;

public class Ready extends Ongoing {

    @Override
    public Status bowl(Pin pin) {
        if (pin.isAllPinsDown()) {
            return new Strike();
        }
        return new FirstBowl(pin);
    }

    @Override
    public Score getScore() {
        return new Score(0, 0);
    }

    @Override
    public Score addScore(Score score) {
        return score;
    }
}
