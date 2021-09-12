package bowling.domain.bowling;

public class Pin {

    private static final String INVALIE_PIN_VALUE_ERROR_MESSAGE = "핀은 0~10 사이의 값만 저장할 수 있다.";

    private final int pin;

    private Pin(int pin) {
        this.pin = pin;
    }

    public static Pin of(int pin) {
        checkPinInputValue(pin);

        return new Pin(pin);
    }

    private static void checkPinInputValue(int pin) {
        if (pin < 0 || pin > 10) {
            throw new RuntimeException(INVALIE_PIN_VALUE_ERROR_MESSAGE);
        }
    }

}
