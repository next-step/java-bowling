package bowling.domain;

import java.util.Objects;

public class FinalFrame implements Frame {

    private Pin pin;
    private final States states;

    public FinalFrame(){
        this.pin = new Pin(Pin.MIN_PIN);
        this.states = new States();
    }

    @Override
    public void bowl(Pin pin) {
    }

    @Override
    public boolean isEndFrame() {
        return false;
    }

    @Override
    public boolean isEndGame() {
        return false;
    }

    @Override
    public Frame getNextFrame(int frameNumber) {
        return null;
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
