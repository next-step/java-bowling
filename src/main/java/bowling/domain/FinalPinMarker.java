package bowling.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class FinalPinMarker implements PinMarker {

    private static final int MAX_MARKS = 3;
    private PinMarks marks;
    private PinMarkerState state;

    FinalPinMarker() {
        this.marks = new PinMarks(MAX_MARKS);
        this.state = new Ready();
    }

    @Override
    public PinMarkerState getState() {
        return state;
    }

    /**
     * @param pinMark
     */
    @Override
    public void mark(PinMark pinMark) {
        state = state.mark(pinMark);
    }

    @Override
    public long getCountOfMarks() {
        if( state instanceof LastBonus)
            return 2;
        if( state instanceof Bonus)
            return 1;
        if( state instanceof Ready)
            return 0;
        if( state instanceof SecondMark)
            return 1;
        return 3;
    }

    public boolean checkPin(int pos, PinMark test){
        if( getCountOfMarks() > 0
        && getCountOfMarks() > pos ){
            return marks.get(pos).equals(test);
        }
        return false;
    }

    @Deprecated
    @Override
    public boolean isStrike() {
        return checkPin(0, PinMark.strike);
    }

    @Deprecated
    @Override
    public boolean isSpare() {
        if (marks.getAll().size() > 1) {
            return marks.get(marks.getAll().size() - 1).getCountOfFallDownPins()
                    + marks.get(marks.getAll().size() - 2).getCountOfFallDownPins()
                    == PinMark.MAX_PINS;
        }
        return false;
    }

    @Override
    public int getCountOfAllFallDownPins() {
        return marks.getCountOfMarked();
    }

    @Override
    public boolean isCompleted() {
        return state.isCompleted();
    }

    @Override
    public boolean isStarted() {
        return state.isStarted();
    }

    @Override
    public List<PinMark> toList() {
        return marks.getAll();
    }

    @Override
    public List<PinMarkSign> toSigns() {
        if (isSpare() && getCountOfMarks() == 2) {
            return Arrays.asList(PinMarkSign.number(marks.get(0).getCountOfFallDownPins()), PinMarkSign.Spare);
        }
        if (isSpare() && getCountOfMarks() == 3) {
            return Arrays.asList(PinMarkSign.Strike, PinMarkSign.number(marks.get(1).getCountOfFallDownPins()), PinMarkSign.Spare);
        }
        return marks.toSigns();
    }

    /**
     * State 에 따라 mark() , isComplete() 를 구현
     */
    private class Ready extends InProgress {
        @Override
        public PinMarkerState mark(PinMark pinMark) {
            marks.mark(pinMark);
            if(pinMark.isStrike()){
                return new Bonus();
            }
            return new SecondMark(this, pinMark);
        }
    }

    private class Bonus extends InProgress {
        @Override
        public PinMarkerState mark(PinMark pinMark) {
            marks.mark(pinMark);
            if( pinMark.isStrike() ) {
                return new LastBonus();
            }
            return new SecondMark(this, pinMark);
        }
    }

    private class SecondMark extends InProgress {

        private final PinMark firstPinMark;
        private final PinMarkerState prev;

        public SecondMark(PinMarkerState prev, PinMark firstPinMark) {
            this.prev = prev;
            this.firstPinMark = firstPinMark;
        }

        @Override
        public PinMarkerState mark(PinMark pinMark) {
            int sum = firstPinMark.plus(pinMark);
            if ( sum > PinMark.MAX_PINS ){
                throw new IllegalArgumentException("SecondMark 에서 쓰러뜨린 총 pin 의 수는 " + PinMark.MAX_PINS + " 개를 넘을 수 없습니다");
            }

            marks.mark(pinMark);

            if( prev instanceof Ready && sum == PinMark.MAX_PINS ){
                return new LastBonus();
            }

            return new Completed();
        }
    }

    private class LastBonus extends InProgress {
        @Override
        public PinMarkerState mark(PinMark pinMark) {
            marks.mark(pinMark);
            return new Completed();
        }
    }
}
