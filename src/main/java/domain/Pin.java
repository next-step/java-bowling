package domain;

public class Pin {
    private int pin = 10;

    private Pin(int ball) {
        this.pin = pin - ball;
    }

    public static Pin firstPin(int ball) {
        return new Pin(ball);
    }

    public Pin secondPin(int ball) {
        this.pin = pin - ball;
        return this;
    }

    public int getPin() {
        return this.pin;
    }
}
