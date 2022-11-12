package bowling;

public class BowlingGame {
    public static final int FRAME_SIZE = 10;
    private final Frames frames;
    private FrameNumber frameNumber;

    public BowlingGame() {
        this.frames = new Frames(FRAME_SIZE);
        this.frameNumber = FrameNumber.first();
    }

    public FrameNumber pitchingBall(int hitCount) {
        if (frameNumber.isSameNumber(FRAME_SIZE)) {
            return frames.pitchingLast(frameNumber, hitCount);
        }
        this.frameNumber = frames.pitching(frameNumber, hitCount);
        return this.frameNumber;
    }

    public Frames getFrames() {
        return frames;
    }
}