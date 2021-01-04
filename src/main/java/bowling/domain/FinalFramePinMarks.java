package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFramePinMarks implements PinMarks {

    private final int MAX_MARKS = 3;
    private List<PinMark> marks = new ArrayList<>();

    FinalFramePinMarks() {
    }

    /**
     * @param pin
     */
    @Override
    public void mark(PinMark pin) {
        shouldMarksLessThanMaxMarks();
        if (!isStrike()) shouldMarkedPinSumLessThanOrEqualMaxPins(pin);
        shouldMarkedPinsEqualPinMaxIfThirdPinMark();

        marks.add(pin);
        markBonusToEmptyIfTwoShotSumIsLessThanPinMax();
    }

    @Override
    public long getCountOfMarks() {
        return marks.stream()
                .filter(mask -> !(mask instanceof BlankPinMark))
                .count();
    }

    @Override
    public boolean isStrike() {
        if (marks.size() > 0)
            return marks.get(0).getCountOfFallDownPins() == PinMark.MAX_PINS;
        return false;
    }

    @Override
    public boolean isSpare() {
        if (marks.size() > 1) {
            return marks.get(marks.size() - 1).getCountOfFallDownPins() + marks.get(marks.size() - 2).getCountOfFallDownPins() == PinMark.MAX_PINS;
        }
        return false;
    }

    private void shouldMarkedPinsEqualPinMaxIfThirdPinMark() {
        if (marks.size() == 2
                && getCountOfAllFallDownPins() < PinMark.MAX_PINS) {
            throw new IllegalStateException("3번째 PinMark 는 1,2번째 PinMark 의 합이 10이상 일때 mark 할 수 있습니다");
        }
    }

    private void markBonusToEmptyIfTwoShotSumIsLessThanPinMax() {
        if (marks.size() == 2
                && getCountOfAllFallDownPins() < PinMark.MAX_PINS) {
            marks.add(PinMark.blank);
        }
    }


    @Override
    public int getCountOfAllFallDownPins() {
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
    public List<PinMark> toList() {
        return Collections.unmodifiableList(marks);
    }

    @Override
    public List<PinMarkSign> toSigns() {
        if (isSpare() && marks.size() == 2) {
            return Arrays.asList(PinMarkSign.number(marks.get(0).getCountOfFallDownPins()), PinMarkSign.Spare);
        }
        if (isSpare() && marks.size() == 3) {
            return Arrays.asList(PinMarkSign.Strike, PinMarkSign.number(marks.get(1).getCountOfFallDownPins()), PinMarkSign.Spare);
        }
        return marks.stream()
                .filter(mark -> !(mark instanceof BlankPinMark))
                .map(mark -> PinMarkSign.number(mark.getCountOfFallDownPins()))
                .collect(Collectors.toList());
    }
}
