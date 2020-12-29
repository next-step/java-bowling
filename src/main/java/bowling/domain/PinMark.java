package bowling.domain;

import java.util.Objects;

public interface PinMark {

    int MAX_PINS = 10;

    int getCountOfFallDownPins();

    static PinMark empty() {
        return () -> 0;
    }

    static PinMark pin(int countOfFallDownPins) {
        if (countOfFallDownPins > MAX_PINS) throw new IllegalArgumentException("한번에 쓰러뜨릴 수 있는 최대 볼링핀은 10개 입니다");
        return new DefaultPinMark(countOfFallDownPins);
    }

    PinMark strike = pin(MAX_PINS);

}

class DefaultPinMark implements PinMark {

    private int countOfFallDownPins;

    public DefaultPinMark(int countOfFallDownPins) {
        this.countOfFallDownPins = countOfFallDownPins;
    }

    @Override
    public int getCountOfFallDownPins() {
        return countOfFallDownPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultPinMark that = (DefaultPinMark) o;
        return countOfFallDownPins == that.countOfFallDownPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfFallDownPins);
    }
}

