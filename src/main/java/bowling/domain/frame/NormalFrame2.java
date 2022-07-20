package bowling.domain.frame;

import java.util.List;

public class NormalFrame2 extends Frame2 {

    @Override
    public Frame2 askCurrentFrame() {
        if (restOfPins == 0) {
            Frame2 nextFrame = new NormalFrame2();
            next = nextFrame;
            nextFrame.prev = this;
            return nextFrame;
        }
        return this;
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
