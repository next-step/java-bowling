package bowling.domain.state;

import bowling.exception.ImpossiblePitchException;
import bowling.domain.Pins;

public class Strike implements State {

    private static final String STRIKE = "X";

    private Strike() {
    }

    public static State create() {
        return new Strike();
    }

    @Override
    public State pitch(Pins pins) {
        throw new ImpossiblePitchException(Strike.class.getName());
    }

    @Override
    public boolean isFrameEnd() {
        return true;
    }

    @Override
    public String getSymbol() {
        return STRIKE;
    }

}
