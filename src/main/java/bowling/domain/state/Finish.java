package bowling.domain.state;

import bowling.domain.Pins;
import bowling.exception.NextPitchingException;

import java.util.List;

public class Finish implements State {
    private static final String MESSAGE = "10 프레임";

    private final Pins pins;

    public Finish(Pins pins) {
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
    public String display() {
        return pins.display();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public List<State> lastSpare(List<State> list, State state) {
        throw new NextPitchingException(MESSAGE);
    }
}
