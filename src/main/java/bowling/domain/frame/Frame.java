package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;

import java.util.Objects;

public abstract class Frame {

    protected final RoundNumber roundNumber;
    protected final Pins pins;
    protected Frame nextFrame;

    protected Frame(RoundNumber roundNumber, Pins pins) {
        this.roundNumber = roundNumber;
        this.pins = pins;
    }

    public abstract Frame nextFrame();

    public abstract Frame createNextFrame();

    public abstract void knockDownPin(Pin pin);

    public abstract boolean isEnded();

    public boolean roundNumberEquals(RoundNumber roundNumber) {
        return this.roundNumber.equals(roundNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(roundNumber, frame.roundNumber) && Objects.equals(pins, frame.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundNumber, pins);
    }
}
