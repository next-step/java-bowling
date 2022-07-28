package bowling2.domain.frame;

import java.util.List;

public class NormalFrame extends Frame {

    public NormalFrame() {
        super(1);
    }

    public NormalFrame(int index) {
        super(index);
    }

    public NormalFrame(int pins, List<Integer> fallenPins) {
        super(pins, fallenPins);
    }

    public NormalFrame(int index, int restOfPins, List<Integer> fallenPins) {
        super(index, restOfPins, fallenPins);
    }

    public NormalFrame(int index, Frame prev, Frame next) {
        super(index, prev, next);
    }

    public NormalFrame(int index, int restOfPins, List<Integer> fallenPins, Frame prev, Frame next) {
        super(index, restOfPins, fallenPins, prev, next);
    }

    public NormalFrame(int index, int restOfPins, List<Integer> fallenPins, Frame prev, Frame next, int score) {
        super(index, restOfPins, fallenPins, prev, next, score);
    }

    @Override
    public Frame askCurrentFrame() {
        if (moveToFinalFrame()) {
            Frame nextFrame = new FinalFrame(10, this, null);
            this.next = nextFrame;
            return nextFrame;
        }
        if (moveToNextFrame()) {
            Frame nextFrame = new NormalFrame(index + 1, this, null);
            this.next = nextFrame;
            return nextFrame;
        }
        return this;
    }

    @Override
    public void handleAfterTry(int fallenPin) {
        fallenPins.add(fallenPin);
        restOfPins -= fallenPin;
        remainedTryNo--;
    }

    @Override
    public ScoreType scoreType() {
        if (restOfPins > 0) {
            return ScoreType.COMMON;
        }
        if (fallenPins.size() > 1) {
            return ScoreType.SPARE;
        }
        return ScoreType.STRIKE;
    }

    private boolean moveToNextFrame() {
        return remainedTryNo == 0 || restOfPins == 0;
    }

    private boolean moveToFinalFrame() {
        return moveToNextFrame() && index == 9;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
