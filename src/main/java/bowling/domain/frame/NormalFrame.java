package bowling.domain.frame;

import bowling.domain.status.Status;

import java.util.Objects;

public class NormalFrame extends Frame {
    public static final int LAST_FRAME_NO = 9;

    public NormalFrame(int newFrameNo) {
        this.frameNo = newFrameNo;
    }

    @Override
    public Frame bowl() {
        if (frameNo == LAST_FRAME_NO && status.isFinished()) {
            return new FinalFrame();
        }
        if (status.isFinished()) {
            return new NormalFrame(frameNo + 1);
        }
        return this;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return frameNo == that.frameNo && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, status);
    }
}
