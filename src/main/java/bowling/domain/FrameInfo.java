package bowling.domain;

public class FrameInfo {

    private final int frameNumber;
    private final int round;

    private FrameInfo(int frameNumber, int round) {
        this.frameNumber = frameNumber;
        this.round = round;
    }

    public static FrameInfo of(int frameNumber, int round) {
        return new FrameInfo(frameNumber, round);
    }
}
