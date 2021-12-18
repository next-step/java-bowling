package bowling.model;

import java.util.Objects;

public class Pins {

    private static final int MAX_PIN_COUNT = 10;
    private static final int MIN_PIN_COUNT = 0;

    private final int knockedDownPin;

    public Pins(int knockedDownPin) {
        checkPinCount(knockedDownPin);
        this.knockedDownPin = knockedDownPin;
    }

    private void checkPinCount(int knockedDownPin) {
        if(knockedDownPin < MIN_PIN_COUNT || knockedDownPin > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("투구 값은 0과 10 사이의 값이어야 합니다.");
        }
    }

    public Pins(int knockedDownPin1, int knockedDownPin2) {
        checkPinCount(knockedDownPin1, knockedDownPin2);
        this.knockedDownPin = knockedDownPin1 + knockedDownPin2;
    }

    private void checkPinCount(int knockedDownPin1, int knockedDownPin2) {
        if(knockedDownPin1 + knockedDownPin2 > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("투구로 쓰러진 핀의 총 개수는 10개를 넘을 수 없습니다.");
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

    public int getKnockedDownPin() {
        return this.knockedDownPin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins = (Pins) o;
        return knockedDownPin == pins.knockedDownPin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(knockedDownPin);
    }
}
