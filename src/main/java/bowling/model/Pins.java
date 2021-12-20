package bowling.model;

import java.util.Objects;

public class Pins {

    private static final int MAX_PIN_COUNT = 10;
    private static final int MIN_PIN_COUNT = 0;
    private static final String PIN_COUNT_IS_BETWEEN_ZERO_AND_TEN_MESSAGE = "투구 값은 0과 10 사이의 값이어야 합니다.";
    private static final String PIN_COUNT_SUM_CANNOT_OVER_TEN_MESSAGE = "투구로 쓰러진 핀의 총 개수는 10개를 넘을 수 없습니다.";
    private final int knockedDownPin;

    private Pins(int knockedDownPin) {
        whenPinCountNotInValidRangeThrowException(knockedDownPin);
        this.knockedDownPin = knockedDownPin;
    }

    private void whenPinCountNotInValidRangeThrowException(int knockedDownPin) {
        if(knockedDownPin < MIN_PIN_COUNT || knockedDownPin > MAX_PIN_COUNT) {
            throw new IllegalArgumentException(PIN_COUNT_IS_BETWEEN_ZERO_AND_TEN_MESSAGE);
        }
    }

    private Pins(int knockedDownPin1, int knockedDownPin2) {
        whenPinCountNotInValidRangeThrowException(knockedDownPin1, knockedDownPin2);
        this.knockedDownPin = knockedDownPin1 + knockedDownPin2;
    }

    private void whenPinCountNotInValidRangeThrowException(int knockedDownPin1, int knockedDownPin2) {
        if(knockedDownPin1 + knockedDownPin2 > MAX_PIN_COUNT) {
            throw new IllegalArgumentException(PIN_COUNT_SUM_CANNOT_OVER_TEN_MESSAGE);
        }
    }

    public static Pins knockedDown(int knockedDownPin) {
        return new Pins(knockedDownPin);
    }

    public boolean isSpare(Pins firstPin) {
        return new Pins(this.knockedDownPin, firstPin.knockedDownPin).isStrike();
    }

    public boolean isStrike() {
        return this.knockedDownPin == MAX_PIN_COUNT;
    }

    public boolean isGutter() {
        return this.knockedDownPin == MIN_PIN_COUNT;
    }

    public String count() {
        return String.valueOf(this.knockedDownPin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pins pins = (Pins) o;
        return knockedDownPin == pins.knockedDownPin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(knockedDownPin);
    }
}
