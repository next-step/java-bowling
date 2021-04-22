package bowling.domain.state;

public class BowlingPin {
    private static final int MAX_PIN_COUNT = 10;
    private static final int MIN_PIN_COUNT = 0;

    private final int pin;

    private BowlingPin(int pin) {
        this.pin = pin;
    }
}
