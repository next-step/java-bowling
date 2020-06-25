package bowling.domain;

public class NormalFrame implements Frame {
    private Pin pin;
    private final States states;

    public NormalFrame() {
        this.pin = new Pin(0);
        this.states = new States();
    }

    @Override
    public void bowl(Pin fallenPin) {
        State state = State.bowl(this.pin.getFallenPin(), fallenPin.getFallenPin(), this.states.getStatesLength());
        setStates(state);
        setPin(fallenPin);
    }

    @Override
    public boolean isEndFrame() {
        return this.states.getStatesLength() == 2 || State.STRIKE == State.valueOf(this.pin.getFallenPin(), false);
    }

    @Override
    public boolean isEndGame() {
        return false;
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

    public States getStates() {
        return states;
    }
}
