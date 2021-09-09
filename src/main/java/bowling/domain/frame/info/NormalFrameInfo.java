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

    public static NormalFrameInfo of(int frameNumber, int round) {
        return new NormalFrameInfo(frameNumber, round);
    }

    public static NormalFrameInfo create() {
        return of(FIRST_ROUND, FIRST_ROUND);
    }

    @Override
    public NormalFrameInfo nextFrame() {
        return of(frameNumber + 1, FIRST_ROUND);
    }

    @Override
    public NormalFrameInfo nextRound() {
        return of(frameNumber, round + 1);
    }

    @Override
    public boolean isLastRound() {
        return round == LAST_NORMAL_FRAME_ROUND;
    }

    @Override
    public int currentFrameNumber() {
        return frameNumber;
    }

    public boolean isEndFrame() {
        return frameNumber == MAX_NORMAL_FRAME;
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
