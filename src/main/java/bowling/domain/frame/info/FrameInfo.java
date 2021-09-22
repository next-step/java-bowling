package bowling.domain.frame.info;

import java.util.Objects;

public abstract class FrameInfo implements Comparable<FrameInfo> {

    public static final int SECOND_ROUND = 1;
    public static final int START_ROUND = 0;

    private final int frameNumber;
    private final int round;

    protected FrameInfo(int frameNumber, int round) {
        this.frameNumber = frameNumber;
        this.round = round;
    }

    public abstract FrameInfo nextFrame();

    public abstract FrameInfo nextRound();

    public abstract boolean isLastRound();

    public int round() {
        return round;
    }

    public int frameNumber() {
        return frameNumber;
    }

    public boolean isAfterFrame(int currentFrame) {
        return frameNumber > currentFrame;
    }

    @Override
    public int compareTo(FrameInfo o) {
        if (this.frameNumber == o.frameNumber()) {
            return Integer.compare(round, o.round());
        }

        return Integer.compare(frameNumber, o.frameNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FrameInfo frameInfo = (FrameInfo) o;
        return frameNumber == frameInfo.frameNumber && round == frameInfo.round;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, round);
    }

}
