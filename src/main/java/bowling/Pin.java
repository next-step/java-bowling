package bowling;

public class Pin {

    private final static int MAX_PINS = 10;
    private final static int MIN_PINS = 0;
    private final int pin;

    private Pin(int pin) {
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }

    private static void validate(int pin) throws IllegalArgumentException {
        if (pin < MIN_PINS || pin > MAX_PINS) {
            throw new IllegalArgumentException("입력된 핀 갯수가 범위를 벗어났습니다.");
        }
    }

    public static Pin of(int pin) {
        validate(pin);
        return new Pin(pin);
    }
}
