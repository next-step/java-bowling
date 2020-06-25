package bowling.domain;

import java.util.Objects;

public class FinalFrame implements Frame {
    private static final int FRAME_MAX_LENGTH = 3;

    private Pin pin;
    private final States states;

    public FinalFrame() {
        this.pin = new Pin(Pin.MIN_PIN);
        this.states = new States();
    }

    @Override
    public void bowl(Pin fallenPin) {
        State state = State.bowl(this.pin.getFallenPin(), fallenPin.getFallenPin(), this.states.getStatesLength(), true);
        setStates(state);
        setPin(fallenPin);
    }

    @Override
    public boolean isEndFrame() {
        return this.states.getStatesLength() > FRAME_MAX_LENGTH;
    }

    @Override
    public boolean isEndGame() {
        if(states.getStatesLength() == NormalFrame.FRAME_MAX_LENGTH && states.getStates().stream().mapToInt(State::getFallenPins).sum() < 10) {
            return true;
        }
        return this.states.getStatesLength() == FRAME_MAX_LENGTH;
    }

    @Override
    public Frame getNextFrame(int frameNumber) {
        return null;
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
        if (!(o instanceof FinalFrame)) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(pin, that.pin) &&
                Objects.equals(states, that.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin, states);
    }
}
