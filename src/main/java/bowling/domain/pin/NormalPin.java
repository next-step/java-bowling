package bowling.domain.pin;

import java.util.Objects;

public class NormalPin implements Pin {
    public static final int MAX_PIN_COUNT = 10;
    public static final int MIN_PIN_COUNT = 0;

    private int pin;

    private NormalPin(int countOfDownPin) {
        this.pin = countOfDownPin;
    }

    public static NormalPin from(int countOfDownPin) {
        if (countOfDownPin > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("핀은 최대 10개까지 쓰러뜨릴 수 있습니다.");
        }
        if (countOfDownPin < MIN_PIN_COUNT) {
            throw new IllegalArgumentException("핀은 최소 0개까지 쓰러뜨릴 수 있습니다.");
        }
        return new NormalPin(countOfDownPin);
    }

    @Override
    public int getPin() {
        return pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalPin normalPin1 = (NormalPin) o;
        return pin == normalPin1.pin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin);
    }
}
