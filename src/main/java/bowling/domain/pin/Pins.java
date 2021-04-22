package bowling.domain.pin;

import bowling.exception.PinsOutOfSizeException;

import java.util.Objects;

import static bowling.domain.pin.Pin.BOWLING_PIN_MAX_SIZE;
import static bowling.domain.pin.Pin.BOWLING_PIN_MIN_SIZE;

public class Pins {

    public static final String PINS_FIRST_STRIKE_AND_CAN_STRIKE = "초구가 스트라이크인 경우에만 2구에 10개를 쓰러뜨릴 수 있습니다.";
    public static final String PINS_ERR_OUT_OF_SIZE = "2구 까지 핀의 합이 10을 넘을 수 없습니다.";
    private final Pin firstPin;
    private final Pin secondPin;

    private Pins(final Pin firstPin, final Pin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public static Pins init() {
        return new Pins(Pin.of(BOWLING_PIN_MIN_SIZE), Pin.of(BOWLING_PIN_MIN_SIZE));
    }

    public static Pins valueOf(final int firstPin, final int secondPin) {
        return new Pins(Pin.of(firstPin), Pin.of(secondPin));
    }

    public Pins first(final int firstPin) {
        return Pins.valueOf(firstPin, BOWLING_PIN_MIN_SIZE);
    }

    public Pins second(final int secondPin) {
        if (firstPin.isOutOfSize(secondPin)) {
            throw new PinsOutOfSizeException(PINS_ERR_OUT_OF_SIZE);
        }
        return new Pins(firstPin, Pin.of(secondPin));
    }

    public Pins last(final int downPins) {
        if (firstPin.isLessThanMaxSize() && downPins == BOWLING_PIN_MAX_SIZE) {
            throw new PinsOutOfSizeException(PINS_FIRST_STRIKE_AND_CAN_STRIKE);
        }
        return new Pins(firstPin, Pin.of(downPins));
    }

    private int first() {
        return firstPin.value();
    }

    private int second() {
        return secondPin.value();
    }

    public int normalScore() {
        return first() + second();
    }

    public boolean isStrike() {
        return firstPin.value() == BOWLING_PIN_MAX_SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Pins)) return false;
        final Pins pins = (Pins) o;
        return Objects.equals(firstPin, pins.firstPin)
                && Objects.equals(secondPin, pins.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }
}
