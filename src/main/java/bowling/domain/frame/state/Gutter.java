package bowling.domain.frame.state;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.exception.BowlingException;

public class Gutter implements State {

    private final Pins pins;

    public Gutter() {
        this.pins = new Pins(Pin.from(), Pin.from());
    }

    @Override
    public State bowl(final int pinsCount) {
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
