package bowling.domain.frame.state;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.exception.BowlingException;

public class Ready implements State {

    @Override
    public State bowl(final int pinsCount) {
        Pin pin = Pin.from().bowl(pinsCount);

        if (pin.isFinish()) {
            return new Strike();
        }

        return new FirstBowl(pin);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score getCurrentScore() {
        throw new BowlingException();
    }

    @Override
    public Score getCalculateScore(Score before) {
        throw new BowlingException();
    }

    @Override
    public Pins getPins() {
        throw new BowlingException();
    }
}
