package bowling.domain.frame.info;

import java.util.Objects;

public class NormalFrameInfo implements FrameInfo {

    private static final int LAST_NORMAL_FRAME_ROUND = 1;
    private static final int MAX_NORMAL_FRAME = 8;

    private final int frameNumber;
    private final int round;

    private NormalFrameInfo(int frameNumber, int round) {
        this.frameNumber = frameNumber;
        this.round = round;
    }

    public static FrameInfo of(int frameNumber, int round) {
        return new NormalFrameInfo(frameNumber, round);
    }

    public static FrameInfo create() {
        return of(0, 0);
    }

    @Override
    public FrameInfo nextFrame() {
        return of(frameNumber + 1, round);
    }

    @Override
    public FrameInfo nextRound() {
        return of(frameNumber, round + 1);
    }

    @Override
    public boolean isLastRound() {
        return round == LAST_NORMAL_FRAME_ROUND;
    }

    @Override
    public boolean isEndFrame() {
        return frameNumber == MAX_NORMAL_FRAME;
    }

    public boolean isFirstRound() {
        return frameNumber == FIRST_ROUND;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NormalFrameInfo that = (NormalFrameInfo) o;
        return frameNumber == that.frameNumber && round == that.round;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, round);
    }

}
