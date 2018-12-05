package domain;

public class Pin {

    private static final int DEFAULT_ZERO = 0;

    private int pin = 10;
    private int ball;

    private Pin(int ball) {
        this.ball = ball;
        this.pin = pin - ball;
    }

    public static Pin firstPin(int ball) {
        return new Pin(ball);
    }

    public Pin secondPin(int ball) {
        this.ball = ball;
        this.pin = pin - ball;
        return this;
    }

    public int getPin() {
        return this.pin;
    }

    public boolean isSamePinCount(int pinCount) {
        if (this.pin == pinCount) {
            return true;
        }
        return false;
    }

    public boolean isBallCountZero() {
        if (this.ball == DEFAULT_ZERO) {
            return true;
        }
        return false;
    }
}
