package bowling.domain.state;

import bowling.domain.Pins;
import bowling.exception.NextPitchingException;

import java.util.List;


public class Strike implements State {
    private static final String MESSAGE = "스트라이크";

    private final Pins pins;

    public Strike(Pins pins) {
        this.pins = pins;
    }

    @Override
    public State nextPitch(int pins) {
        throw new NextPitchingException(MESSAGE);
    }

    @Override
    public State lastPitch(int pins) {
        throw new NextPitchingException(MESSAGE);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public List<State> lastSpare(List<State> list, State state) {
        throw new NextPitchingException(MESSAGE);
    }

    @Override
    public int firstPins() {
        return pins.getFalledPins();
    }

    @Override
    public int secondPins() {
        return 0;
    }
}
