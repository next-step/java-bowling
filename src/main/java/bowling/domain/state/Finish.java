package bowling.domain.state;

import bowling.domain.Pins;
import bowling.exception.NextPitchingException;

public class Finish implements State{
    private final Pins pins;

    public Finish(Pins pins) {
        this.pins = pins;
    }

    @Override
    public State nextPitch(int pins) {
        throw new NextPitchingException("10 프레임");
    }

    @Override
    public State lastPitch(int pins) {
        throw new NextPitchingException("10 프레임");
    }

    @Override
    public String display() {
        return pins.display();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
