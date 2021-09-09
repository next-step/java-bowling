package bowling.domain.frame;

import java.util.Objects;

public abstract class Frame {
    protected int frameNo;

    public abstract Frame next(int number);

    public int getFrameNo() {
        return frameNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return frameNo == frame.frameNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo);
    }
}
