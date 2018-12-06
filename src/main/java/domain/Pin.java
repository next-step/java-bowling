package domain;

public class Pin {

    private static final int DEFAULT_ZERO = 0;

    private int pin = 10;
    private int ball;

    private Pin(int ball) {
        this.ball = ball;
        this.pin = pin - ball;
    }

    private Pin(Pin pin) {
        this.pin = pin.pin;
        this.ball = pin.ball;
    }

    public static Pin of(int ball) {
        return new Pin(ball);
    }

    public static Pin firstPin(int ball) {
        return new Pin(ball);
    }

    public static Pin copyOf(Pin pin) {
        return new Pin(pin);
    }

    public Pin secondPin(int ball) {
        this.ball = ball;
        this.pin = pin - ball;
        return this;
    }

    public boolean isAllPinDown() {
        if (this.pin == DEFAULT_ZERO) {
            return true;
        }
        return false;
    }

    public boolean isAllPinUp() {
        if (this.ball == DEFAULT_ZERO) {
            return true;
        }
        return false;
    }

    public int getPin() {
        return pin;
    }
}
