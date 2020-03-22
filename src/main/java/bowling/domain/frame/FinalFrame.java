package bowling.domain.frame;

import bowling.domain.framestatus.FrameStatus;

import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {

    private int frameNo;
    private List<Pin> pins;
    private FrameStatus frameStatus;

    public FinalFrame(int frameNo) {
        this.frameNo = frameNo;
    }

    @Override
    public void bowl(int countOfHit) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return frameNo == that.frameNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo);
    }
}
