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
        State state = State.finalBowl(this.pin.getFallenPin(), fallenPin.getFallenPin(), this.states.getLastState());
        setStates(state);
        setPin(fallenPin);
    }

    @Override
    public boolean isEndFrame() {
        return this.states.getSize() > FRAME_MAX_LENGTH;
    }

    @Override
    public boolean isEndGame() {
        if (states.getSize() == NormalFrame.FRAME_MAX_LENGTH && getStatesPinSum() < Pin.MAX_PIN) {
            return true;
        }
        return this.states.getSize() == FRAME_MAX_LENGTH;
    }

    @Override
    public Frame getNextFrame(int frameNumber) {
        throw new UnsupportedOperationException();
    }

    private int getStatesPinSum() {
        return this.states.getStates()
                .stream()
                .mapToInt(State::getFallenPins)
                .sum();
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
