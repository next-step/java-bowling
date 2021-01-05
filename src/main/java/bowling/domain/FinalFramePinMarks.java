package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FinalFramePinMarks implements PinMarks {

    private static final int MIN_MARKS = 2;
    private static final int MAX_MARKS = 3;
    private List<PinMark> marks = new ArrayList<>();
    private boolean complete = false;

    FinalFramePinMarks() {
    }

    /**
     * @param pin
     */
    @Override
    public void mark(PinMark pin) {
        shouldCountOfMarksLessThanMaxMarks();
        if (getCountOfMarks() == 1  && !isFirstStrike() ){
            shouldSumOfLastTwoPinMarksLessThanOrEqualMaxPins(marks.get(0), pin);
        }
        if (getCountOfMarks() == 2 && isFirstStrike() && !isSecondStrike() ) {
            shouldSumOfLastTwoPinMarksLessThanOrEqualMaxPins(marks.get(1), pin);
        }

        marks.add(pin);
        completePinMarksIf( pinMarks -> isMiss() || isMaxMarked() );
    }

    private boolean isMiss() {
        return getCountOfMarks() == MIN_MARKS
                && getCountOfAllFallDownPins() < PinMark.MAX_PINS;
    }

    private boolean isMaxMarked() {
        return getCountOfMarks() == MAX_MARKS;
    }

    private void completePinMarksIf(Predicate<PinMarks> condition) {
        if( condition.test(this) ){
            complete = true;
        }
    }

    @Override
    public long getCountOfMarks() {
        return marks.size();
    }

    public boolean checkPin(int pos, PinMark test){
        if( getCountOfMarks() > 0
        && getCountOfMarks() > pos ){
            return marks.get(pos).equals(test);
        }
        return false;
    }

    @Override
    public boolean isFirstStrike() {
        return checkPin(0, PinMark.strike);
    }

    private boolean isSecondStrike(){
        return checkPin(1, PinMark.strike);
    }

    @Override
    public boolean isSpare() {
        if (marks.size() > 1) {
            return marks.get(marks.size() - 1).getCountOfFallDownPins() + marks.get(marks.size() - 2).getCountOfFallDownPins() == PinMark.MAX_PINS;
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
        return complete;
    }

    @Override
    public List<PinMark> toList() {
        return Collections.unmodifiableList(marks);
    }

    @Override
    public List<PinMarkSign> toSigns() {
        if (isSpare() && getCountOfMarks() == 2) {
            return Arrays.asList(PinMarkSign.number(marks.get(0).getCountOfFallDownPins()), PinMarkSign.Spare);
        }
        if (isSpare() && getCountOfMarks() == 3) {
            return Arrays.asList(PinMarkSign.Strike, PinMarkSign.number(marks.get(1).getCountOfFallDownPins()), PinMarkSign.Spare);
        }
        return marks.stream()
                .map(mark -> PinMarkSign.number(mark.getCountOfFallDownPins()))
                .collect(Collectors.toList());
    }
}
