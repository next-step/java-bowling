package bowling.domain.frame;

import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractFrame implements Frame {

    public static final int FIRST_FRAME_NUMBER = 1;
    public static final int LAST_FRAME_NUMBER = 10;

    protected final int frameNumber;

    protected Frame nextFrame;

    public AbstractFrame(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    @Override
    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public Frame getNextFrame() {
        return nextFrame;
    }

    @Override
    public Iterator<Frame> iterator() {
        return new FrameIterator(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return frameNumber == that.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }

}
