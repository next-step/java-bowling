package bowling.domain.frame.state;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.exception.BowlingException;

public class FirstBowl implements State {

    public static final String PINS_STATE = "%3d   ";

    private final Pins pins;

    public FirstBowl(final Pin firstPin) {
        this.pins = new Pins(firstPin, null);
    }

    private void validatePinCount(int pinCount) {
        if (pinCount > 10) {
            throw new BowlingException(Pin.PINS_COUNT_RANGE);
        }
    }

    @Override
    public State bowl(int pinsCount) {
        validatePinCount(pinsCount);
        Pin second = Pin.from().bowl(pinsCount);
        Pins throwResult = this.pins.addSecondPin(second);

        if (throwResult.isFinish()) {
            return new Spare(throwResult);
        }

        if (throwResult.isMiss()) {
            return new Gutter();
        }

        if (throwResult.isGutter()) {
            return new Gutter();
        }

        return new Miss(throwResult);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

//    @Override
//    public String getCurrentPinsState() {
//        if (firstPin.isGutter()) {
//            return "  -  ";
//        }
//        return String.format(PINS_STATE, firstPin.getDownPin());
//    }

    @Override
    public Score getCurrentScore() {
        return new Score(pins.getFirstDownPin(), 0);
    }

    @Override
    public Score getCalculateScore(Score before) {
        return before.addScore(new Score(pins.getFirstDownPin()));
    }

    @Override
    public Pins getPins() {
        return pins;
    }
}
