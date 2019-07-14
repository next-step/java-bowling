package domain;

import View.OutView;

import java.util.Optional;

public class Frame {
    private static final String MISS = "Miss";
    private static final String STRIKE = "Strike";
    private static final String SPARE = "Spare";
    private static final String FIRST_BOWL = "FirstBowl";
    private static final String FINAL_BOWL = "FinalBowl";
    private static final boolean FIRST_IS_NOT_SPARE = false;
    public static final int NO_MORE_NEXT = -1;
    public static final int ONE = 1;
    public static final int ZERO = 0;

    private State state;
    private Frame next;

    public Frame() {
        next = null;
        FrameCounter.addFrameCount();
    }

    public Frame(int frameCount) {
        this();
        new FrameCounter(frameCount);
    }

    public Frame doBowling(int point) {
        int fellPins = Pin.of(point).getFellPins();

        if (isNullState()) {
            state = new Ready().bowl(fellPins);
            return this;
        }
        if (state.isNameOfState(FIRST_BOWL) || state.isNameOfState(FINAL_BOWL)) {
            state = state.bowl(fellPins);
            return this;
        }
        next = new Frame();
        next.doBowling(fellPins);
        return next;
    }

    public String getPoint() {
        Pin firstPin = Pin.of(state.getFirstPin());
        Pin secondPin = Pin.of(state.getSecondPin());

        int firstPins = firstPin.getFellPins();
        int secondPins = secondPin.getFellPins();

        if (state.isNameOfState(FIRST_BOWL)) {
            firstPins = state.getFellPins();
            secondPins = NO_MORE_NEXT;
        }

        String first = PointName.valueOfPointName(firstPins, FIRST_IS_NOT_SPARE);
        String pointResult = first;

        if (!state.isNameOfState(STRIKE)) {
            String second = PointName.valueOfPointName(secondPins, firstPin.isSpare(secondPin));
            pointResult += "|" + second;
        }
        return String.format("%-4s", pointResult);
    }

    public int getScore() {
        Score score = createScore();
        if (score.canCalculateScore()) {
            return score.getScore();
        }

        Optional<Frame> maybeNext = Optional.ofNullable(next);
        if (maybeNext.isPresent()) {
            return maybeNext.get().calculateAdditionalScore(score);
        }
        return NO_MORE_NEXT;
    }

    private int calculateAdditionalScore(Score beforeScore) {
        int before = beforeScore.getScore();
        int now = this.getScore();

        if(now == NO_MORE_NEXT) {
            return now;
        }
        return before + now;
    }

    private Score createScore() {
        Pin firstPin = Pin.of(state.getFirstPin());
        Pin secondPin = Pin.of(state.getSecondPin());

        if (FrameCounter.isFinalFrame()) {
            return finalScore(firstPin, secondPin);
        }
        return normalScore(firstPin, secondPin);
    }

    private Score finalScore(Pin firstPin, Pin secondPin) {
        final int TWICE_LEFT = 3;

        int left = TWICE_LEFT;
        if (firstPin != null) {
            left += firstPin.isStrike() ? ZERO : NO_MORE_NEXT;
        }
        if (secondPin != null) {
            left += firstPin.isSpare(secondPin) ? ZERO : NO_MORE_NEXT;
        }

        return new Score(state.getFellPins(), left);
    }

    private Score normalScore(Pin firstPin, Pin secondPin) {
        if (firstPin.isStrike()) {
            return Score.ofStrike();
        }
        if (firstPin.isSpare(secondPin)) {
            return Score.ofSpare();
        }
        if (firstPin.isMiss(secondPin)) {
            return Score.ofMiss(state.getFellPins());
        }
        return new Score(firstPin.getFellPins(), ONE);
    }

    public boolean nowPlaying() {
        if (isNullState()) {
            return Boolean.TRUE;
        }
        if (state.isNameOfState(MISS) || state.isNameOfState(SPARE) || state.isNameOfState(STRIKE)) {
            return Boolean.FALSE;
        }
        if (state.isNameOfState(FINAL_BOWL)) {
            return state.nowPlaying();
        }
        return Boolean.TRUE;
    }

    public boolean isFinalFrame() {
        if (isNullState()) {
            return Boolean.FALSE;
        }
        if (state.isNameOfState(FINAL_BOWL)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Frame getNext() {
        return next;
    }

    private boolean isNullState() {
        if (state == null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
