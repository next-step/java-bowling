package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneralFramePinMarks implements PinMarks {
    private final int MAX_MARKS = 2;
    private List<PinMark> marks = new ArrayList<>();

    @Override
    public void mark(PinMark pin) {
        shouldMarksLessThanMaxMarks();
        shouldMarkedPinSumLessThanOrEqualMaxPins(pin);

        marks.add(pin);
        markRemainingToEmptyIfFirstMarkIsStrike();
    }

    @Override
    public boolean isStrike() {
        if(isAllMarked())
            return marks.get(0).equals(PinMark.strike);
        return false;
    }

    private void markRemainingToEmptyIfFirstMarkIsStrike() {
        if (marks.size() == 1
                && marks.get(0).equals(PinMark.strike)) {
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
