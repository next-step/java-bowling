package bowling;

public class BowlingGame {

    private final UserName userName;
    private final Frames frames;

    public BowlingGame(UserName userName, Frames frames) {
        this.userName = userName;
        this.frames = frames;
    }

    public void bowl(Pin falledPins) {
        if (!isFinished()) {
            frames.bowl(falledPins);
        }
    }

    public int currentFrameNumber() {
        return frames.currentFrameNumber();
    }

    public boolean isFinished() {
        return frames.isFinished();
    }

    public Frames getFrames() {
        return frames;
    }

    public UserName getUserName() {
        return userName;
    }
}
