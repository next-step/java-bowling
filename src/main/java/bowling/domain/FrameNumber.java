package bowling.domain;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-17 00:36
 */
public class FrameNumber {
    public static final int INIT_FRAME_NUMBER = 1;
    public static final int NEXT_FRAME_NUMBER = 1;
    public static final int LAST_FRAME_NUMBER = 10;
    public static final int NORMAL_FRAME_MAX_NUMBER = 9;
    private final int frameNumber;

    private FrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public static FrameNumber init() {
        return new FrameNumber(INIT_FRAME_NUMBER);
    }

    public FrameNumber next() {
        if (frameNumber >= LAST_FRAME_NUMBER) {
            throw new IllegalStateException("10프레임까지만 게임할 수 있습니다.");
        }
        return new FrameNumber(frameNumber + NEXT_FRAME_NUMBER);
    }

    public boolean isOver() {
        return frameNumber == LAST_FRAME_NUMBER;
    }

    public boolean isNormalFrameOver() {
        return frameNumber == NORMAL_FRAME_MAX_NUMBER;
    }

    @Override
    public String toString() {
        return "FrameNumber{" +
                "frameNumber=" + frameNumber +
                '}';
    }
}
