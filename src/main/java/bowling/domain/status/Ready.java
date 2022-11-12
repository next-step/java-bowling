package bowling.domain.status;

import bowling.domain.Pin;
import bowling.domain.Score;

public class Ready extends Status {

    @Override
    public Status bowl(Pin pin) {
        if (pin.isAllPinsDown()) {
            return new Strike();
        }
        return new FirstBowl(pin);
    }

    @Override
    public boolean isFinished() {
        return false;
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
