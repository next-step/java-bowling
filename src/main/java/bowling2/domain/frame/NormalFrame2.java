package bowling2.domain.frame;

import java.util.List;

public class NormalFrame2 extends Frame2 {

    @Override
    public Frame2 askCurrentFrame() {
        if (moveToFinalFrame()) {
            Frame2 nextFrame = new FinalFrame2(10, this, null);
            this.next = nextFrame;
            return nextFrame;
        }
        if (moveToNextFrame()) {
            Frame2 nextFrame = new NormalFrame2(index + 1, this, null);
            this.next = nextFrame;
            return nextFrame;
        }
        return this;
    }

    private boolean moveToNextFrame() {
        return restOfPins == 0;
    }

    private boolean moveToFinalFrame() {
        return moveToNextFrame() && index == 9;
    }

    public NormalFrame2() {
        super(1);
    }

    public NormalFrame2(int index) {
        super(index);
    }

    public NormalFrame2(int pins, List<Integer> fallenPins) {
        super(pins, fallenPins);
    }

    public NormalFrame2(int index, int restOfPins, List<Integer> fallenPins) {
        super(index, restOfPins, fallenPins);
    }

    public NormalFrame2(int index, Frame2 prev, Frame2 next) {
        super(index, prev, next);
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
