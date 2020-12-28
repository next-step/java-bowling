package bowling.domain;

import java.util.List;

public interface PinMarks {

    default void shouldLessThanMaxMarks(List<PinMark> marks) {
        if (marks.size() >= getMaxMarks()) throw new IllegalStateException("더 이상 PinMark 를 추가할 수 없습니다");
    }

    int getMaxMarks();

    void mark(PinMark pin);

    int getCountOfFallDownPins();

    boolean isAllMarked();

    List<PinMark> toImmutableList();
}
