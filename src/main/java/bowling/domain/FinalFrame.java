package bowling.domain;

import java.util.Objects;

public class FinalFrame extends Frame {
    private static final int FINAL_FRAME_MAX_LENGTH = 3;

    public FinalFrame() {
        this.pin = new Pin(Pin.MIN_PIN);
        this.states = new States();
        this.nextFrame = null;
    }

    @Override
    public void bowl(Pin fallenPin) {
        State state = State.finalBowl(this.pin.getFallenPin(), fallenPin.getFallenPin(), this.states.getLastState());
        setStates(state, fallenPin);
        setPin(fallenPin);
    }

    @Override
    public boolean isEndFrame() {
        return this.states.getSize() > FINAL_FRAME_MAX_LENGTH;
    }

    @Override
    public boolean isEndGame() {
        if (states.getSize() == NormalFrame.NORMAL_FRAME_MAX_LENGTH && states.getStatesPinSum() < Pin.MAX_PIN) {
            return true;
        }
        return this.states.getSize() == FINAL_FRAME_MAX_LENGTH;
    }

    @Override
    public Frame getNextFrame(int frameNumber) {
        throw new UnsupportedOperationException();
    }

    @Override
    public States getStates() {
        return states;
    }

    @Override
    public int getScore() {
        if (!isEndGame()) {
            return WAITING_CALCULATION;
        }

        return states.getPins()
                .stream()
                .mapToInt(Pin::getFallenPin)
                .sum();
    }

    private void setStates(State state, Pin fallenPin) {
        this.states.add(state, fallenPin);
    }

    private void setPin(Pin fallenPin) {
        this.pin = fallenPin;
    }

    public Pin getPin() {
        return pin;
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
