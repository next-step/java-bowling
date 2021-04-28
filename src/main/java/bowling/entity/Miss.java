package bowling.entity;

import java.util.Objects;

import static bowling.entity.Pin.MAX_PIN_COUNT;

public class Miss implements Score {
    private final Pin firstPin;
    private final Pin secondPin;

    public Miss(Pin firstPin, Pin secondPin) {
        validatePinResult(firstPin, secondPin);
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    private void validatePinResult(Pin firstPin, Pin secondPin) {
        if (firstPin.sumPin(secondPin) > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("프레임 투구의 합은 10을 넘을 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return Objects.equals(firstPin, miss.firstPin) && Objects.equals(secondPin, miss.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }
}
