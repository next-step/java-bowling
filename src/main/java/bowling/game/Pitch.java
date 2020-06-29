package bowling.game;

import bowling.game.vo.Pin;

public class Pitch {
    private final Pin pinCount;
    private final State state;

    private Pitch(final Pin pinCount, final State state) {
        this.pinCount = pinCount;
        this.state = state;
    }

    public static Pitch firstPitch(final int pinCount) {
        Pin pin = new Pin(pinCount);
        State state = State.findState(pin, new Pin(Pin.PIN_COUNT_MIN), false);

        return new Pitch(pin, state);
    }

    public Pitch nextPitch(final int pinCount) {
        Pin pin = new Pin(pinCount);
        State state = State.findState(pin, this.pinCount, true);

        return new Pitch(pin, state);
    }

    public int getPinCount() {
        return pinCount.getPinCount();
    }

    public State getState() {
        return state;
    }

    public boolean isStrikePitch() {
        return state.isStrike();
    }

    public boolean isSparePitch() {
        return state.isSpare();
    }

    public String stateToString() {
        if (state.isMiss()) {
            return String.valueOf(pinCount);
        }

        return this.state.getSymbol();
    }
}
