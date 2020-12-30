package bowling.domain;

import java.util.List;

public interface PinMarks {

    default void shouldMarksLessThanMaxMarks() {
        if (isAllMarked()) throw new IllegalStateException("더 이상 PinMark 를 추가할 수 없습니다");
    }

    default void shouldMarkedPinSumLessThanOrEqualMaxPins(PinMark secondPinMark) {
        if (toImmutableList().size() == 1
                && getCountOfFallDownPins() + secondPinMark.getCountOfFallDownPins() > PinMark.MAX_PINS) {
            throw new IllegalArgumentException("2번째 PinMark 와 첫번째 PinMark 가 쓰러뜨린 pin 수는 " + PinMark.MAX_PINS + " 개를 넘을 수 없습니다");
        }
    }

    default void mark(int countOfFallDown){
        mark(PinMark.pin(countOfFallDown));
    }

    void mark(PinMark pin);

    boolean isStrike();

    boolean isSpare();

    int getCountOfFallDownPins();

    boolean isAllMarked();

    List<PinMark> toImmutableList();

    List<PinMarkSign> toSigns();
}
