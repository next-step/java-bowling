package bowlingRefactor;

public class FrameNumber {

    public static final int MAX_FRAME_NUMBER = 10;
    public static final int MIN_FRAME_NUMBER = 1;
    public static final String VALIDATION_FRAME_NUMBER_MESSAGE = "프레임은 1~10 사이입니다.";

    private int frameNumber;

    private FrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public static FrameNumber of(int frameNumber) {
        if (frameNumber < MIN_FRAME_NUMBER  || frameNumber > MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException(VALIDATION_FRAME_NUMBER_MESSAGE);
        }
        return new FrameNumber(frameNumber);
    }

    public boolean isFinalFrame() {
        return frameNumber == MAX_FRAME_NUMBER;
    }
}
