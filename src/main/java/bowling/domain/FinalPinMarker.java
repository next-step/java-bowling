package bowling.domain;

import java.util.Arrays;
import java.util.List;

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
    public List<PinMarkSymbol> toSigns() {
        if (isSpare() && getCountOfMarks() == 2) {
            return Arrays.asList(PinMarkSymbol.from(marks.get(0).getCountOfFallDownPins()), PinMarkSymbol.Spare);
        }
        if (isSpare() && getCountOfMarks() == 3) {
            return Arrays.asList(PinMarkSymbol.Strike, PinMarkSymbol.from(marks.get(1).getCountOfFallDownPins()), PinMarkSymbol.Spare);
        }
        return marks.toSymbols();
    }

    /**
     * State 에 따라 mark() , isComplete() 를 구현
     */
    private class Ready extends BeforeStart {

        @Override
        public PinMarkerState mark(PinMark pinMark) {
            marks.mark(pinMark);
            if(pinMark.isStrike()){
                return new Bonus(marks);
            }
            return new SecondMark(marks, this, pinMark);
        }
    }

    private class Bonus extends InProgress {

        private final PinMarks marks;

        public Bonus(PinMarks marks) {
            this.marks = marks;
        }

        @Override
        public PinMarkerState mark(PinMark pinMark) {
            marks.mark(pinMark);
            if( pinMark.isStrike() ) {
                return new LastBonus(marks);
            }
            return new SecondMark(marks, this, pinMark);
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return Arrays.asList(PinMarkSymbol.Strike);
        }
    }

    private class SecondMark extends InProgress {
        private final PinMarks marks;
        private final PinMark firstPinMark;
        private final PinMarkerState prev;

        public SecondMark(PinMarks marks, PinMarkerState prev, PinMark firstPinMark) {
            this.marks = marks;
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
                return new LastBonus(marks);
            }

            return new Miss(marks);
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return marks.toSymbols();
        }
    }

    private class Miss extends Completed {
        public Miss(PinMarks marks) {
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return Arrays.asList();
        }
    }

    private class LastBonus extends InProgress {
        private final PinMarks marks;

        public LastBonus(PinMarks marks) {
            this.marks = marks;
        }

        @Override
        public PinMarkerState mark(PinMark pinMark) {
            marks.mark(pinMark);
            return new CompletedHasBonus(marks);
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return null;
        }
    }

    private class CompletedHasBonus extends Completed {

        private final PinMarks marks;

        public CompletedHasBonus(PinMarks marks) {
            this.marks = marks;
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return null;
        }
    }

}
