package domain;

import java.util.Optional;

import static domain.Score.ONCE_LEFT;
import static domain.Score.TWICE_LEFT;

public class Frame {
    private static final String FIRST_BOWL = "FirstBowl";
    private static final String FINAL_BOWL = "FinalBowl";
    public static final boolean FIRST_IS_NOT_SPARE = false;
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
        return this;
    }

    public String getPoint() {
        return state.getPoint();
    }

    public int getScore() {
        Score score = state.getScore();
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
        int previousScore = beforeScore.getScore();
        int currentScore = getLeftScore(state, beforeScore.getLeft());
        return currentScore == NO_MORE_NEXT ? NO_MORE_NEXT : previousScore + currentScore;
    }

    private int getLeftScore(State currentState, int left) {
        if (left == ONCE_LEFT) {
            Pin first = currentState.getFirstPin();
            return first.getFellPins();
        }
        if (left == TWICE_LEFT && (currentState.getSecondPin() != null || Optional.ofNullable(next).isPresent())) {
            Pin first = currentState.getFirstPin();
            return first.getFellPins() + getTwiceLeftScore(currentState);
        }
        return NO_MORE_NEXT;
    }

    private int getTwiceLeftScore(State currentState) {
        Optional<Pin> maybeSecondPin = Optional.ofNullable(currentState.getSecondPin());
        if (maybeSecondPin.isPresent()) {
            Pin second = maybeSecondPin.get();
            return second.getFellPins();
        }
        Optional<Frame> maybeNextFrame = Optional.ofNullable(next);
        if (maybeNextFrame.isPresent()) {
            Frame nextFrame = maybeNextFrame.get();
            State nextFrameState = nextFrame.getState();
            Pin nextFrameStatePin = nextFrameState.getFirstPin();
            return nextFrameStatePin.getFellPins();
        }
        return ZERO;
    }

    public boolean isFrameEnd() {
        return state.isFrameEnd();
    }

    public boolean isFinalFrame() {
        if (isNullState()) {
            return Boolean.FALSE;
        }
        if (FrameCounter.isFinalFrame()) {
            return isFrameEnd();
        }
        return Boolean.FALSE;
    }

    public Frame createNext() {
        next = new Frame();
        return next;
    }

    private boolean isNullState() {
        if (state == null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public State getState() {
        return state;
    }

    public Frame getNext() {
        return next;
    }
}
