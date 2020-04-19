package bowling.domain.frame.state;

import bowling.domain.Pins;
import bowling.domain.score.Score;
import bowling.exception.BowlingException;

public class Gutter implements State {

    private static final String GUTTER_DASH = " -|-  ";
    private static final String PINS_STATE = "%3d|-  ";

    private final Pins firstPins;
    private final Pins secondPins;

    public Gutter() {
        this.firstPins = Pins.from();
        this.secondPins = Pins.from();
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
    public String getCurrentPinsState() {
        if (firstPins.isGutter()) {
            return GUTTER_DASH;
        }
        return String.format(PINS_STATE, firstPins.getDownPin(), secondPins.getDownPin());
    }

    @Override
    public Score getScore() {
        return new Score(Pins.MIN_PIN);
    }

    @Override
    public Score getFirstScore() {
        return new Score(0);
    }
}
