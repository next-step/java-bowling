package bowling.domain;

import java.util.Arrays;

public interface PinMark {

    int MAX_PINS = 10;

    int getCountOfFallDownPins();

    static PinMark empty() {
        return () -> 0;
    }

    static PinMark pin(int countOfFallDownPins) {
        if (countOfFallDownPins > MAX_PINS) throw new IllegalArgumentException("한번에 쓰러뜨릴 수 있는 최대 볼링핀은 10개 입니다");

        PinMark mark = PreservedPinMark.valueOfPins(countOfFallDownPins);
        if (mark == null) {
            return () -> countOfFallDownPins;
        }
        return mark;
    }

    static PinMark strike() {
        return PreservedPinMark.Strike;
    }
}


enum PreservedPinMark implements PinMark {
    Strike(10), Spare(9);

    private final int countOfFallDownPins;

    PreservedPinMark(int countOfFallDownPins) {
        this.countOfFallDownPins = countOfFallDownPins;
    }

    public static PreservedPinMark valueOfPins(int countOfFallDownPins) {
        return Arrays.stream(PreservedPinMark.values())
                .filter(mark -> mark.countOfFallDownPins == countOfFallDownPins)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int getCountOfFallDownPins() {
        return countOfFallDownPins;
    }
}


