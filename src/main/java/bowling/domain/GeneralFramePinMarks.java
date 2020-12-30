package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GeneralFramePinMarks implements PinMarks {
    private final int MAX_MARKS = 2;
    private List<PinMark> marks = new ArrayList<>();

    GeneralFramePinMarks() { }

    @Override
    public void mark(PinMark pin) {
        shouldMarksLessThanMaxMarks();
        shouldMarkedPinSumLessThanOrEqualMaxPins(pin);

        marks.add(pin);
        markRemainingToBlankIfFirstMarkIsStrike();
    }

    @Override
    public boolean isStrike() {
        if(isAllMarked())
            return marks.get(0).equals(PinMark.pin(10));
        return false;
    }

    @Override
    public boolean isSpare() {
        if(marks.size() == 2){
            return getCountOfFallDownPins() == PinMark.MAX_PINS;
        }
        return false;
    }

    private void markRemainingToBlankIfFirstMarkIsStrike() {
        if (marks.size() == 1
                && marks.get(0).equals(PinMark.pin(10))) {
            marks.add(PinMark.blank());
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

    @Override
    public List<PinMarkSign> toSigns() {
        if (isStrike()) {
            return Arrays.asList(PinMarkSign.Strike);
        }
        if (isSpare()) {
            return Arrays.asList(PinMarkSign.number(marks.get(0).getCountOfFallDownPins()), PinMarkSign.Spare);
        }

        return marks.stream()
                .map(mark -> PinMarkSign.number(mark.getCountOfFallDownPins()))
                .collect(Collectors.toList());
    }
}
