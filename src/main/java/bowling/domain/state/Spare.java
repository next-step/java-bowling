package bowling.domain.state;

import bowling.domain.Pins;
import bowling.exception.NextPitchingException;

public class Spare implements State{
    private static final String MESSAGE = "스페어";

    private final Pins firstPins;
    private final Pins secondPins;

    public Spare(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public State nextPitch(int pins) {
        throw new NextPitchingException(MESSAGE);
    }

    @Override
    public String display() {
        return firstPins.display(secondPins);
    }
}
