package bowling.domain.frame;

public class FrameNumber {

    private static final int MIN_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 10;

    private final int frameNumber;

    private FrameNumber(int frameNumber) {
        validateNumber(frameNumber);
        this.frameNumber = frameNumber;
    }

    public static FrameNumber create(final int frameNumber) {
        return new FrameNumber(frameNumber);
    }

    private void validateNumber(int frameNumber) {
        if (frameNumber < MIN_FRAME_NUMBER) {
            throw new IllegalArgumentException("프레임은 1보다 작을 수 없습니다.");
        }

        if (frameNumber > MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException("프레임은 10보다 클 수 없습니다.");
        }
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public String toString() {
        return String.valueOf(frameNumber);
    }
}
