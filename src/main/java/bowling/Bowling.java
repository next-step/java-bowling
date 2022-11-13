package bowling;

public class Bowling {

    private final UserName userName;
    private final Frames frames;

    public Bowling(UserName userName, Frames frames) {
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
}
