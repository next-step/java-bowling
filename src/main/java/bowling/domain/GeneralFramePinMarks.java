package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneralFramePinMarks implements PinMarks {
    private final int MAX_MARKS = 2;
    private List<PinMark> marks = new ArrayList<>();

    @Override
    public int getMaxMarks() {
        return MAX_MARKS;
    }

    @Override
    public void mark(PinMark pin) {
        shouldLessThanMaxMarks(marks);
        if (marks.size() == 1
                && getCountOfFallDownPins() + pin.getCountOfFallDownPins() > PinMark.MAX_PINS) {
            throw new IllegalArgumentException("두번째 PinMark 와 첫번째 PinMark 가 쓰러뜨린 pin 수는 " + PinMark.MAX_PINS + " 개를 넘을 수 없습니다");
        }

        marks.add(pin);
        markRemainingToEmptyIfFirstShotIsStrike();
    }

    private void markRemainingToEmptyIfFirstShotIsStrike() {
        if (marks.size() == 1
                && getCountOfFallDownPins() == PinMark.MAX_PINS) {
            marks.add(PinMark.empty());
        }
    }

    @Override
    public int getCountOfFallDownPins() {
        return marks.stream()
                .map(PinMark::getCountOfFallDownPins)
                .reduce(Integer::sum)
                .orElse(0);
    }

    @Override
    public boolean isAllMarked() {
        return marks.size() == MAX_MARKS;
    }

    @Override
    public List<PinMark> toImmutableList() {
        return Collections.unmodifiableList(marks);
    }
}
