package bowling.domain;

import java.util.List;

public interface PinMarks {

    default void shouldCountOfMarksLessThanMaxMarks() {
        if (isCompleted()) throw new IllegalStateException("더 이상 PinMark 를 추가할 수 없습니다");
    }

    default void mark(int countOfFallDown) {
        mark(PinMark.pin(countOfFallDown));
    }

    void mark(PinMark pin);

    long getCountOfMarks();

    boolean isFirstStrike();

    boolean isSpare();

    int getCountOfAllFallDownPins();

    /**
     * pin marking 완료여부
     *
     * @return
     */
    boolean isCompleted();

    List<PinMark> toList();

    List<PinMarkSign> toSigns();
}
