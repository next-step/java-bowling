package bowling.domain;

import java.util.Arrays;
import java.util.List;

public class NormalPinMarker implements PinMarker {

    private static final int MAX_MARKS = 2;

    private final PinMarks marks;
    private PinMarkerState state;

    NormalPinMarker() {
        this.marks = new PinMarks(MAX_MARKS);
        this.state = new Ready();
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

    @Override
    public boolean isStrike() {
        return state instanceof Strike;
    }

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

    @Deprecated
    @Override
    public List<PinMark> toList() {
        return marks.getAll();
    }

    @Override
    public List<PinMarkSign> toSigns() {
        if (isStrike()) {
            return Arrays.asList(PinMarkSign.Strike);
        }
        if (isSpare()) {
            return Arrays.asList(PinMarkSign.number(marks.get(0).getCountOfFallDownPins()), PinMarkSign.Spare);
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
                return new Strike();
            }
            return new SecondMark();
        }
    }

    private class SecondMark extends InProgress {
        @Override
        public PinMarkerState mark(PinMark pinMark) {
            if( marks.isSumGreaterThanMaxPins(pinMark) ){
                throw new IllegalArgumentException("SecondMark 에서 쓰러뜨린 총 pin 의 수는 " + PinMark.MAX_PINS + " 개를 넘을 수 없습니다");
            }

            if(marks.mark(pinMark) == PinMark.MAX_PINS){
                return new Spare();
            }
            return new Miss();
        }
    }

    private class Spare extends Completed { }

    private class Strike extends Completed { }

    private class Miss extends Completed { }
}



