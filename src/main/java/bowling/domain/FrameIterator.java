package bowling.domain;

import java.util.Iterator;
import java.util.Objects;

public class FrameIterator implements Iterator<Frame> {

    private Frame frame;
    private int frameNumber = 0;

    public FrameIterator(Frame frame) {
        this.frame = frame;
    }

    @Override
    public boolean hasNext() {
        if (frameNumber == 0) {
            return true;
        }
        return Objects.nonNull(frame.getNextFrame());
    }

    @Override
    public Frame next() {
        if (frameNumber == 0) {
            frameNumber++;
            return frame;
        }
        for (int i = 0; i < frameNumber; i++) {
            frame = frame.getNextFrame();
        }
        return frame;
    }

}
