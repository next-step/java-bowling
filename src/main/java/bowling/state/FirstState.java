package bowling.state;

public class FirstState implements State {

    private Pin firstPin;

    private FirstState(Pin firstPin) {
        this.firstPin = firstPin;
    }

    public static FirstState of(int value) {
        Pin firstPin = Pin.of(value);
        return new FirstState(firstPin);
    }

    @Override
    public String expression() {
        return firstPin.toString();
    }
}
