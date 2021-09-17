package bowling.domain.score;

import bowling.exception.BowlingPinException;

import java.util.Objects;

public class Pin {
    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private int pins;

    public Pin(int number){
        validate(number);
        pins = number;
    }

    private void validate(int number){
        if(number > MAX_PINS){
            throw new BowlingPinException("볼링핀은 10개 이상 쓰러트릴 수 없습니다.");
        }
        if(number < MIN_PINS){
            throw new BowlingPinException("쓰러트린 볼링핀 개수는 -가 될 수 없습니다.");
        }
    }

    public int count() {
        return pins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return pins == pin.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
