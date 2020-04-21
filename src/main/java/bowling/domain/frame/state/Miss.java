package bowling.domain.frame.state;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.exception.BowlingException;

public class Miss implements State {

    public static final String PINS_STATE = "%3d|%d ";

    //    private final Pin firstPin;
//    private final Pin secondPin;
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

//    @Override
//    public String getCurrentPinsState() {
//        if (firstPin.isGutter()) {
//            return String.format(" -|%d  ", secondPin.getDownPin());
//        }
//
//        if (secondPin.isGutter()) {
//            return String.format("  %d|- ", firstPin.getDownPin());
//        }
//
//        return String.format(PINS_STATE, firstPin.getDownPin(), secondPin.getDownPin());
//    }

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
