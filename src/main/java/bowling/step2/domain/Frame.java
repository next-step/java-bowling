package bowling.step2.domain;

public class Frame {
    private final int frame;
    private final FrameScore frameScore;

    private Frame (int frame, FrameScore frameScore) {
        this.frame = frame;
        this.frameScore = frameScore;
    }

    public static Frame of (int frame, FrameScore frameScore) {
        return new Frame(frame, frameScore);
    }

    public static Frame init (int frame) {
        return of(frame, null);
    }
}