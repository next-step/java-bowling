package bowling.domain.frame;

import bowling.domain.status.BowlStatus;

import java.util.Objects;

public class NormalFrame implements Frame {
    public static final int LAST_FRAME_NO = 9;
    private final int frameNo;
    private final BowlStatus state;

    public NormalFrame(int frameNo, BowlStatus state) {
        this.frameNo = frameNo;
        this.state = state;
    }

    @Override
    public Frame bowl() {
        if (frameNo == LAST_FRAME_NO && isFinished()) {
            System.out.println("final frame");
            return new FinalFrame();
        }
        if (isFinished()) {
            System.out.println("normal frame " + frameNo + 1);
            return new NormalFrame(frameNo + 1, BowlStatus.NORAML);
        }
        return this;
    }

    private boolean isFinished() {
        return state == BowlStatus.STRIKE || state == BowlStatus.SPARE || state == BowlStatus.MISS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return frameNo == that.frameNo && state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNo, state);
    }
}
