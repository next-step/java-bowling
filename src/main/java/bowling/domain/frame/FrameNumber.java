package bowling.domain.frame;

public class FrameNumber {
    public static final String OVER_NORMAL_FRAME_NO_ERROR = "일반 Frame은 최대 9개까지만 생성할 수 있습니다.";
    public static final int MAX_NORMAL_FRAME_COUNT = 9;
    private int frameNumber;

    public FrameNumber(int frameNumber) {
        assertFrameNo(frameNumber);

        this.frameNumber = frameNumber;
    }

    private void assertFrameNo(int frameNumber) {
        if (frameNumber > MAX_NORMAL_FRAME_COUNT) {
            throw new IllegalArgumentException(OVER_NORMAL_FRAME_NO_ERROR);
        }
    }

    public boolean isMaxNormalFrameCount() {
        return frameNumber == MAX_NORMAL_FRAME_COUNT;
    }

    public FrameNumber getNext() {
        return new FrameNumber(frameNumber + 1);
    }

    public int getValue() {
        return frameNumber;
    }
}
