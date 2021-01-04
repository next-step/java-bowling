package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NormalFramePinMarks implements PinMarks {

    private final int MAX_MARKS = 2;
    private List<PinMark> marks = new ArrayList<>();
    private boolean completed = false;

    NormalFramePinMarks() {
    }

    @Override
    public void mark(PinMark pin) {
        shouldCountOfMarksLessThanMaxMarks();
        if( getCountOfMarks() == 1 ) shouldSumOfLastTwoPinMarksLessThanOrEqualMaxPins(marks.get(0), pin);

        marks.add(pin);
        completeIf(pinMarks -> isFirstStrike() || isMaxMarked() );
    }

    private boolean isMaxMarked() {
        return getCountOfMarks() == MAX_MARKS;
    }

    private void completeIf(Predicate<PinMarks> condition){
        if( condition.test(this) ){
            completed = true;
        }
    }

    @Override
    public long getCountOfMarks() {
        return marks.size();
    }

    @Override
    public boolean isFirstStrike() {
        return getCountOfMarks() > 0 && marks.get(0).equals(PinMark.strike);
    }

    @Override
    public boolean isSpare() {
        if (getCountOfMarks() == MAX_MARKS
                && getCountOfAllFallDownPins() == PinMark.MAX_PINS) {
            return true;
        }
        return false;
    }

    @Override
    public int getCountOfAllFallDownPins() {
        return marks.stream()
                .map(PinMark::getCountOfFallDownPins)
                .reduce(Integer::sum)
                .orElse(0);
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public List<PinMark> toList() {
        return Collections.unmodifiableList(marks);
    }

    @Override
    public List<PinMarkSign> toSigns() {
        if (isFirstStrike()) {
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
