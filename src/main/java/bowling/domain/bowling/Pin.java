package bowling.domain.bowling;

public class Pin {

    private static final String INVALID_PIN_VALUE_ERROR_MESSAGE = "핀은 0~10 사이의 값만 저장할 수 있다.";
    private static final String INVALID_SECOND_PIN_ERROR_MESSAGE = "첫번째 핀이 쓰러뜨리고 남은 핀 개수만 저장할 수 있다.";

    private static final int PIN_MIN_VALUE = 0;
    private static final int PIN_MAX_VALUE = 10;

    private final int first;
    private final int second;

    private Pin(int first) {
        this(first, PIN_MIN_VALUE);
    }

    private Pin(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public static Pin of(int pin) {
        checkPinInputValue(pin);

        return new Pin(pin);
    }

    public static Pin from(Pin input, int second) {
        checkPinInputValue(second);
        checkSecondPin(input, second);

        return new Pin(input.first, second);
    }

    private static void checkPinInputValue(int pin) {
        if (pin < PIN_MIN_VALUE || pin > PIN_MAX_VALUE) {
            throw new RuntimeException(INVALID_PIN_VALUE_ERROR_MESSAGE);
        }
    }

    private static void checkSecondPin(Pin first, int second) {
        if (second > Math.subtractExact(PIN_MAX_VALUE, first.first)) {
            throw new RuntimeException(INVALID_SECOND_PIN_ERROR_MESSAGE);
        }
    }


}
