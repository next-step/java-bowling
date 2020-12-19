package bowling.domain;

import bowling.domain.interfaces.State;

public class NormalFrame {

    private Pins pins = new Pins();
    private State state = new FirstPitch();

    public void pitch(int count) {
        state = state.bowl(pins, count);
    }
}
