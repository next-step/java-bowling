package bowling.domain.state;

import bowling.domain.Pins;
import bowling.exception.NextPitchingException;

public class Miss implements State {
    private static final String MESSAGE = "두번째 투구";

    private final Pins firstPins;
    private final Pins secondPins;

    public Miss(Pins firstPins, Pins secondPins) {
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

    @Override
    public boolean isFinished() {
        return true;
    }
}
