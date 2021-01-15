package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public long getCountOfMarks() {
        if (state instanceof LastBonus)
            return 2;
        if (state instanceof Bonus)
            return 1;
        if (state instanceof Ready)
            return 0;
        if (state instanceof Second)
            return 1;
        return 3;
    }

    public boolean checkPin(int pos, PinMark test) {
        if (getCountOfMarks() > 0
                && getCountOfMarks() > pos) {
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
    public List<PinMarkSymbol> toSymbols() {
        return state.toSymbols();
    }

    /**
     * State 에 따라 mark() , isComplete() 를 구현
     */
    private class Ready extends BeforeStart {

        @Override
        public PinMarkerState mark(PinMark pinMark) {
            marks.mark(pinMark);
            if (pinMark.isStrike()) {
                return new BonusOne();
            }
            return new Second(pinMark);
        }
    }

    private class BonusOne extends InProgress {

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

    private class BonusTwo extends InProgress {

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

    private class Second extends InProgress {
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
                return new Bonus(marks, firstPinMark);
            }
            //
            return new Miss();
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return marks.toSymbols();
        }
    }

    private class Bonus extends InProgress {

        private final PinMarks marks;
        private final PinMark firstPinMark;

        public Bonus(PinMarks marks, PinMark firstPinMark) {
            this.marks = marks;
            this.firstPinMark = firstPinMark;
        }

        @Override
        public PinMarkerState mark(PinMark pinMark) {
            marks.mark(pinMark);
            if (pinMark.isStrike()) {
                return new SpareStrike(marks, firstPinMark);
            }
            return new SpareMiss(marks,firstPinMark, pinMark);
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return Arrays.asList(firstPinMark.toSymbol(), PinMarkSymbol.Spare);
        }
    }

    private class LastSecond extends InProgress {
        private final PinMark firstPinMark;

        public LastSecond(PinMark firstPinMark) {
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
                return new StrikeSpare();
            }
            return new StrikeMiss();
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return null;
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
            return marks.stream()
                    .limit(1)
                    .map(PinMark::toSymbol)
                    .collect(Collectors.toList());
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

    private class Miss extends Completed {
        @Override
        public List<PinMarkSymbol> toSymbols() {
            return marks.toSymbols();
        }
    }

    private class SpareStrike extends Completed {

        private final PinMarks marks;
        private final PinMark firstPinMark;

        public SpareStrike(PinMarks marks, PinMark firstPinMark) {
            this.marks = marks;
            this.firstPinMark = firstPinMark;
        }

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return Arrays.asList(firstPinMark.toSymbol(), PinMarkSymbol.Spare, PinMarkSymbol.Strike);
        }
    }

    private class SpareMiss extends Completed {

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

    private class Double extends Completed {
        @Override
        public List<PinMarkSymbol> toSymbols() {
            return marks.toSymbols();
        }
    }

    private class Turkey extends Completed {
        @Override
        public List<PinMarkSymbol> toSymbols() {
            return marks.toSymbols();
        }
    }

    private class StrikeSpare extends Completed {

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

    private class StrikeMiss extends Completed {

        @Override
        public List<PinMarkSymbol> toSymbols() {
            return marks.toSymbols();
        }
    }
}
