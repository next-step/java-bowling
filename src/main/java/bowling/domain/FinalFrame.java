package bowling.domain;

public class FinalFrame extends Frame {

    private FrameNumber frameNumber;

    private FinalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
    }

    public static FinalFrame of(FrameNumber frameNumber) {
        checkFrameNumber(frameNumber);
        return new FinalFrame(frameNumber);
    }

    private static void checkFrameNumber(FrameNumber frameNumber) {
        if (!frameNumber.isFinalFrameNumber()) {
            throw new IllegalArgumentException("마지막 프레임 번호는 10이어야 합니다.");
        }
    }
}
