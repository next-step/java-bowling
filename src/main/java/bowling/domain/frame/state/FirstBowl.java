package bowling.domain.frame.state;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.exception.BowlingException;

import java.util.Objects;

public class FirstBowl implements State {

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

        if (throwResult.isSpare()) {
            return new Spare(throwResult);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstBowl firstBowl = (FirstBowl) o;
        return Objects.equals(pins, firstBowl.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
