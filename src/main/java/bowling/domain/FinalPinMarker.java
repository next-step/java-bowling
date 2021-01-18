package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FinalPinMarker implements PinMarker {

    private static final int MAX_MARKS = 3;

    private PinMarks marks;
    private PinMarkerState state;

    public FinalPinMarker() {
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
    private class Ready extends InProgress {

        @Override
        public PinMarkerState mark(PinMark pinMark) {
            marks.mark(pinMark);
            if (pinMark.isStrike()) {
                return new BonusOne();
            }
            return new Second(pinMark);
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return marks.toSymbols();
        }
    }

    class BonusOne extends InProgress {

        @Override
        public PinMarkerState mark(PinMark pinMark) {
            marks.mark(pinMark);
            if (pinMark.isStrike()) {
                return new BonusTwo();
            }
            return new LastSecond(pinMark);
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return marks.toSymbols();
        }
    }

    class BonusTwo extends InProgress {

        @Override
        public PinMarkerState mark(PinMark pinMark) {
            marks.mark(pinMark);
            if (pinMark.isStrike()) {
                return new Turkey();
            }
            return new Double();
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return Arrays.asList(PinMarkSymbol.Strike, PinMarkSymbol.Strike);
        }
    }

    class Second extends InProgress {
        private final PinMark firstPinMark;

        public Second(PinMark firstPinMark) {
            this.firstPinMark = firstPinMark;
        }

        @Override
        public PinMarkerState mark(PinMark secondPinMark) {
            int sum = firstPinMark.plus(secondPinMark);
            if (sum > PinMark.MAX_PINS) {
                throw new IllegalArgumentException("SecondMark 에서 쓰러뜨린 총 pin 의 수는 " + PinMark.MAX_PINS + " 개를 넘을 수 없습니다");
            }

            marks.mark(secondPinMark);
            if (sum == PinMark.MAX_PINS) {
                return new Bonus(firstPinMark);
            }
            //
            return new Miss();
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return marks.toSymbols();
        }
    }

    class Bonus extends InProgress {
        private final PinMark firstPinMark;

        public Bonus( PinMark firstPinMark) {
            this.firstPinMark = firstPinMark;
        }

        @Override
        public PinMarkerState mark(PinMark pinMark) {
            marks.mark(pinMark);
            if (pinMark.isStrike()) {
                return new SpareStrike(firstPinMark);
            }
            return new SpareMiss(marks,firstPinMark, pinMark);
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return Arrays.asList(firstPinMark.toSymbol(), PinMarkSymbol.Spare);
        }
    }

    class LastSecond extends InProgress {
        private final PinMark firstPinMark;

        public LastSecond(PinMark firstPinMark) {
            this.firstPinMark = firstPinMark;
        }

        @Override
        public PinMarkerState mark(PinMark secondPinMark) {
            int sum = firstPinMark.plus(secondPinMark);
            if (sum > PinMark.MAX_PINS) {
                throw new IllegalArgumentException("Second 에서 쓰러뜨린 총 pin 의 수는 " + PinMark.MAX_PINS + " 개를 넘을 수 없습니다");
            }

            marks.mark(secondPinMark);

            if (sum == PinMark.MAX_PINS) {
                return new StrikeSpare();
            }
            return new StrikeMiss();
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return null;
        }
    }

    class Miss extends Completed {
        @Override
        public List<PinMarkSymbol> toSymbols() {
            return marks.toSymbols();
        }
    }

    class SpareStrike extends Completed {

        private final PinMark firstPinMark;

        public SpareStrike( PinMark firstPinMark) {
            this.firstPinMark = firstPinMark;
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return Arrays.asList(firstPinMark.toSymbol(), PinMarkSymbol.Spare, PinMarkSymbol.Strike);
        }
    }

    class SpareMiss extends Completed {

        private final PinMarks marks;
        private final PinMark firstPinMark;
        private final PinMark lastPinMark;

        public SpareMiss(PinMarks marks, PinMark firstPinMark, PinMark lastPinMark) {
            this.marks = marks;
            this.firstPinMark = firstPinMark;
            this.lastPinMark = lastPinMark;
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return Arrays.asList(firstPinMark.toSymbol(), PinMarkSymbol.Spare, lastPinMark.toSymbol() );
        }
    }

    class Double extends Completed {
        @Override
        public List<PinMarkSymbol> toSymbols() {
            return marks.toSymbols();
        }
    }

    class Turkey extends Completed {

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return marks.toSymbols();
        }
    }

    class StrikeSpare extends Completed {

        @Override
        public List<PinMarkSymbol> toSymbols() {
            List<PinMarkSymbol> symbols = new ArrayList<>();
            symbols.addAll(marks.stream()
                    .limit(2)
                    .map(PinMark::toSymbol)
                    .collect(Collectors.toList()));
            symbols.add(PinMarkSymbol.Spare);
            return symbols;
        }
    }

    class StrikeMiss extends Completed {
        @Override
        public List<PinMarkSymbol> toSymbols() {
            return marks.toSymbols();
        }
    }
}
