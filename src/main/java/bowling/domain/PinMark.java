package bowling.domain;

import java.util.Objects;

public class PinMark {

    public static final int MAX_PINS = 10;
    public static final PinMark strike = PinMark.pin(10);

    private int countOfFallDownPins;

    private PinMark(int countOfFallDownPins) {
        if (countOfFallDownPins > MAX_PINS) throw new IllegalArgumentException("한번에 쓰러뜨릴 수 있는 최대 볼링핀은 10개 입니다");
        this.countOfFallDownPins = countOfFallDownPins;
    }

    public int getCountOfFallDownPins() {
        return countOfFallDownPins;
    }

    public boolean isStrike(){
        return countOfFallDownPins == MAX_PINS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PinMark pinMark = (PinMark) o;
        return countOfFallDownPins == pinMark.countOfFallDownPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfFallDownPins);
    }

    public static PinMark pin(int countOfFallDownPins) {
        return new PinMark(countOfFallDownPins);
    }


}

