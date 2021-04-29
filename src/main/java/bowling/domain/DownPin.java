package bowling.domain;

import java.util.Objects;

public class DownPin {
    private final static int MIN_DOWN_PIN = 0;
    private final static int MAX_DOWN_PIN = 10;

    private final int value;

    public DownPin(final int value) {
        validation(value);
        this.value = value;
    }

    private void validation(final int value) {
        if (value < MIN_DOWN_PIN || value > MAX_DOWN_PIN) {
            throw new IllegalArgumentException("쓰러트린 볼링 핀의 개수는 0과 10사이의 숫자여야 합니다.");
        }
    }

    public int count() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DownPin downPin = (DownPin) o;
        return value == downPin.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "DownPin{" +
                "value=" + value +
                '}';
    }
}
