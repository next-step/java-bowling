package bowling.step2.domain;

public class Frame {
    private final int frame;
    private final PlayerName playerName;
    private final FrameScore frameScore;

    private Frame (int frame, PlayerName playerName, FrameScore frameScore) {
        this.frame = frame;
        this.playerName = playerName;
        this.frameScore = frameScore;
    }

    public static Frame of (int frame, PlayerName playerName, FrameScore frameScore) {
        return new Frame(frame, playerName, frameScore);
    }

    public static Frame init (int frame) {
        return of(frame, null, null);
    }
}