package bowling.domain;

import java.util.Objects;

public class NormalFrame implements Frame {
    public static final int FRAME_MAX_LENGTH = 2;
    private static final int LAST_FRAME = 9;

    private Pin pin;
    private final States states;

    public NormalFrame() {
        this.pin = new Pin(Pin.MIN_PIN);
        this.states = new States();
    }

    @Override
    public void bowl(Pin fallenPin) {
        State state = State.bowl(this.pin.getFallenPin(), fallenPin.getFallenPin(), this.states.getStatesLength(), false);
        setStates(state);
        setPin(fallenPin);
    }

    @Override
    public boolean isEndFrame() {
        return this.states.getStatesLength() == FRAME_MAX_LENGTH || State.STRIKE == State.valueOf(this.pin.getFallenPin(), false);
    }

    @Override
    public boolean isEndGame() {
        return false;
    }

    @Override
    public Frame getNextFrame(int frameNumber) {
        if (frameNumber == LAST_FRAME) {
            return new FinalFrame();
        }
        return new NormalFrame();
    }

    private void setStates(State state) {
        this.states.add(state);
    }

    private void setPin(Pin fallenPin) {
        this.pin = fallenPin;
    }

    public Pin getPin() {
        return pin;
    }

    @Override
    public States getStates() {
        return states;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormalFrame)) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(pin, that.pin) &&
                Objects.equals(states, that.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin, states);
    }
}
