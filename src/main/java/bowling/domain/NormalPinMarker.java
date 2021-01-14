package bowling.domain;

import java.util.Arrays;
import java.util.List;

public class NormalPinMarker implements PinMarker {

    private static final int MAX_MARKS = 2;

    private final PinMarks marks;
    private List<PinMarkSymbol> symbols;
    private PinMarkerState state;

    NormalPinMarker() {
        marks = new PinMarks(MAX_MARKS);
        state = new Ready(marks);
    }

    @Override
    public PinMarkerState getState() {
        return state;
    }

    @Override
    public void mark(PinMark pinMark) {
        state = state.mark(pinMark);

    }

    @Deprecated
    @Override
    public long getCountOfMarks() {
        if( state instanceof Strike )
            return 1;
        if( state instanceof Spare )
            return 2;
        if( state instanceof Ready )
            return 0;
        if( state instanceof Miss )
            return 2;
        return 1;
    }

    @Deprecated
    @Override
    public boolean isStrike() {
        return state instanceof Strike;
    }

    @Deprecated
    @Override
    public boolean isSpare() {
        return state instanceof Spare;
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

    @Deprecated
    @Override
    public List<PinMark> toList() {
        return marks.getAll();
    }

    @Override
    public List<PinMarkSymbol> toSigns() {
        return state.toSymbols();
    }

    /**
     * State 에 따라 mark() , isComplete() 를 구현
     */
    static class Ready extends BeforeStart {
        private final PinMarks marks;

        public Ready(PinMarks marks) {
            this.marks = marks;
        }

        @Override
        public PinMarkerState mark(PinMark pinMark) {
            marks.mark(pinMark);
            if(pinMark.getCountOfFallDownPins() == PinMark.MAX_PINS){
                return new Strike();
            }
            return new SecondMark(marks);
        }
    }

    static class SecondMark extends InProgress {

        private final PinMarks marks;

        public SecondMark(PinMarks marks) {
            this.marks = marks;
        }

        @Override
        public PinMarkerState mark(PinMark pinMark) {
            if( marks.isSumGreaterThanMaxPins(pinMark) ){
                throw new IllegalArgumentException("SecondMark 에서 쓰러뜨린 총 pin 의 수는 " + PinMark.MAX_PINS + " 개를 넘을 수 없습니다");
            }

            int firstPins = marks.getCountOfMarked();
            marks.mark(pinMark);
            if(marks.getCountOfMarked() == PinMark.MAX_PINS){
                return new Spare(firstPins);
            }
            return new Miss(marks);
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return marks.toSymbols();
        }
    }

    static class Spare extends Completed {
        private final int firstPins;

        public Spare(int firstPins) {
            this.firstPins = firstPins;
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return Arrays.asList(PinMarkSymbol.from(firstPins), PinMarkSymbol.Spare);
        }
    }

    static class Strike extends Completed {
        @Override
        public List<PinMarkSymbol> toSymbols() {
            return Arrays.asList(PinMarkSymbol.Strike);
        }
    }

    static class Miss extends Completed {

        private final PinMarks marks;

        public Miss(PinMarks marks) {
            this.marks = marks;
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return marks.toSymbols();
        }
    }
}



