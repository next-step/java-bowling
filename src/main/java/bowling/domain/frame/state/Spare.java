package bowling.domain.frame.state;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.exception.BowlingException;

public class Spare implements State {

    private final Pins pins;

    public Spare(Pin firstPin, Pin secondPin) {
        validatePinsCount(firstPin);
        this.pins = new Pins(firstPin, secondPin);
    }

    public Spare(Pins pins) {
        this.pins = pins;
    }

    private void validatePinsCount(Pin firstPin) {
        if (firstPin.isFinish()) {
            throw new BowlingException();
        }
    }

    @Override
    public State bowl(int pinsCount) {
        throw new BowlingException();
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public Score getCurrentScore() {
        return new Score(pins.getDownPins(), 1);
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
