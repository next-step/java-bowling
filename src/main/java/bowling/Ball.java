package bowling;

public class Ball {
    public static final int ALL_PIN_COUNT = 10;
    private static final String PIN_SIZE_ERROR_MESSAGE = "Ball은 10개 이하의 pin만 갖을 수 있습니다.";
    private int pin;

    public Ball(int pin) {
        if (pin > ALL_PIN_COUNT) {
            throw new IllegalArgumentException(PIN_SIZE_ERROR_MESSAGE);
        }

        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }

    public boolean isStrike() {
        return pin == ALL_PIN_COUNT;
    }
}
