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
