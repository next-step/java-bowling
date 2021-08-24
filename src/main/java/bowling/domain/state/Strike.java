package bowling.domain.state;

import bowling.domain.Pins;
import bowling.exception.NextPitchingException;

public class Strike implements State{
    private final Pins pins;

    public Strike(Pins pins) {
        this.pins = pins;
    }

    @Override
    public State nextPitch(int pins) {
        throw new NextPitchingException("스트라이크");
    }

    @Override
    public State lastPitch(int pins) {
        throw new IllegalArgumentException("스트라이크");
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
