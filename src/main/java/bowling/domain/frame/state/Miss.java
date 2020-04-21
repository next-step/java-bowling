package bowling.domain.frame.state;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.exception.BowlingException;

public class Miss implements State {

    private final Pins pins;

    public Miss(final Pin firstPin, final Pin secondPin) {
        this.pins = new Pins(firstPin, secondPin);
    }

    public Miss(Pins pins) {
        this.pins = pins;
    }

    @Override
    public State bowl(int pinsCount) {
        throw new BowlingException(CANT_THROW_BALL);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public Score getCurrentScore() {
        return new Score(pins.getDownPins(), 0);
    }

    @Override
    public Score getCalculateScore(Score before) {
        before = before.addScore(new Score(pins.getFirstDownPin()));

        if (before.canAddNextScore()) {
            return before.addScore(new Score(pins.getSecondDownPin()));
        }

        return before;
    }

    @Override
    public Pins getPins() {
        return pins;
    }
}
