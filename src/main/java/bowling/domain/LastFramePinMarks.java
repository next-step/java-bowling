package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LastFramePinMarks implements PinMarks {

    private final int MAX_MARKS = 3;
    private List<PinMark> marks = new ArrayList<>();

    /**
     * @param pin
     */
    @Override
    public void mark(PinMark pin) {
        shouldMarksLessThanMaxMarks();
        if( !isStrike() ) shouldMarkedPinSumLessThanOrEqualMaxPins(pin);
        shouldMarkedPinsEqualPinMaxIfThirdPinMark();

        marks.add(pin);
        markBonusToEmptyIfTwoShotSumIsLessThanPinMax();
    }

    @Override
    public boolean isStrike() {
        if(marks.size() > 0)
            return marks.get(0).getCountOfFallDownPins() == PinMark.MAX_PINS;
        return false;
    }

    private void shouldMarkedPinsEqualPinMaxIfThirdPinMark() {
        if (marks.size() == 2
                && getCountOfFallDownPins() < PinMark.MAX_PINS) {
            throw new IllegalStateException("3번째 PinMark 는 1,2번째 PinMark 의 합이 10이상 일때 mark 할 수 있습니다");
        }
    }

    private void markBonusToEmptyIfTwoShotSumIsLessThanPinMax() {
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
