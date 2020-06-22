package bowling.domain.frame;

import bowling.domain.frameStatus.NormalFrameStatus;

import java.util.Objects;

public class NormalFrame {
    private final int index;
    private final NormalFrameStatus normalFrameStatus;
    private final NormalFrame previousFrame;

    NormalFrame(int index, NormalFrameStatus normalFrameStatus, NormalFrame previousFrame) {
        this.index = index;
        this.normalFrameStatus = normalFrameStatus;
        this.previousFrame = previousFrame;
    }

    public static NormalFrame start(int numberOfHitPin) {
        return new NormalFrame(1, NormalFrameStatus.bowlFirst(numberOfHitPin), null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return index == that.index &&
                Objects.equals(normalFrameStatus, that.normalFrameStatus) &&
                Objects.equals(previousFrame, that.previousFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, normalFrameStatus, previousFrame);
    }
}
