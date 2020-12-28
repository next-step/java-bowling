package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LastFramePinMarks implements PinMarks {

    private final int MAX_MARKS = 3;
    private List<PinMark> marks = new ArrayList<>();

    @Override
    public int getMaxMarks() {
        return MAX_MARKS;
    }

    /**
     * @param pin
     */
    @Override
    public void mark(PinMark pin) {
        shouldLessThanMaxMarks(marks);
        shouldLessThanOrEqualPinMax(pin);
        shouldGreaterThanOrEqualPinMaxIfExtraMark();

        marks.add(pin);
        markRemainingToEmptyIfTwoShotSumIsLessThanPinMax();
    }

    private void shouldLessThanOrEqualPinMax(PinMark secondPinMark) {
        if (marks.size() == 1
                && marks.get(0) != PinMark.strike()
                && getCountOfFallDownPins() + secondPinMark.getCountOfFallDownPins() > PinMark.MAX_PINS) {
            throw new IllegalArgumentException("2번째 PinMark 와 1번째 PinMark 가 쓰러뜨린 pin 수는 " + PinMark.MAX_PINS + " 개를 넘을 수 없습니다");
        }
    }

    private void shouldGreaterThanOrEqualPinMaxIfExtraMark() {
        if (marks.size() == 2
                && getCountOfFallDownPins() < PinMark.MAX_PINS) {
            throw new IllegalStateException("3번째 PinMark 는 1,2번째 PinMark 의 합이 10이상 일때 mark 할 수 있습니다");
        }
    }

    private void markRemainingToEmptyIfTwoShotSumIsLessThanPinMax() {
        if (marks.size() == 2
                && getCountOfFallDownPins() < PinMark.MAX_PINS) {
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
