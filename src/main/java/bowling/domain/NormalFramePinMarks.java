package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NormalFramePinMarks implements PinMarks {

    private static final int MAX_MARKS = 2;
    private List<PinMark> marks = new ArrayList<>();
    private boolean completed = false;

    NormalFramePinMarks() {
    }

    @Override
    public void mark(PinMark pin) {
        shouldCountOfMarksLessThanMaxMarks();
        shouldNotCountOfAllFallDownPinsGreaterThanMaxPins(pin);

        marks.add(pin);
        completeIf(pinMarks -> isStrike() || isMaxMarked() );
    }

    private void shouldNotCountOfAllFallDownPinsGreaterThanMaxPins(PinMark pin) {
        if ( getCountOfAllFallDownPins() + pin.getCountOfFallDownPins() > PinMark.MAX_PINS) {
            throw new IllegalArgumentException("두 PinMark 가 쓰러뜨린 pin 의 합은 " + PinMark.MAX_PINS + " 개를 넘을 수 없습니다");
        }
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
    public boolean isStrike() {
        return getCountOfMarks() > 0
                && marks.get(0).equals(PinMark.strike);
    }

    @Override
    public boolean isSpare() {
        return getCountOfMarks() == MAX_MARKS
                && getCountOfAllFallDownPins() == PinMark.MAX_PINS;
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
