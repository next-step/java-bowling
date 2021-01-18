package bowling.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class NormalPinMarker implements PinMarker {

    private static final int MAX_MARKS = 2;

    private final PinMarks marks;
    private PinMarkerState state;

    public NormalPinMarker() {
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

    @Override
    public int getCountOfAllFallDownPins() {
        return marks.getCountOfMarked();
    }

    @Override
    public boolean isCompleted() {
        return state.isCompleted();
    }

    @Override
    public List<PinMarkSymbol> toSymbols() {
        return state.toSymbols();
    }

    @Override
    public Stream<PinMark> markStream() {
        return marks.stream();
    }

    /**
     * State 에 따라 mark() , isComplete() 를 구현
     */
    class Ready extends InProgress {
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

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return marks.toSymbols();
        }
    }

    class SecondMark extends InProgress {

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

    class Spare extends Completed {
        private final int firstPins;

        public Spare(int firstPins) {
            this.firstPins = firstPins;
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return Arrays.asList(PinMarkSymbol.from(firstPins), PinMarkSymbol.Spare);
        }
    }

    class Strike extends Completed {
        @Override
        public List<PinMarkSymbol> toSymbols() {
            return Arrays.asList(PinMarkSymbol.Strike);
        }
    }

    class Miss extends Completed {

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



