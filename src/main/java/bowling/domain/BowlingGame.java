package bowling.domain;

public class BowlingGame {

    private final NormalFrames normalFrames;
    private final LastFrame lastFrame;

    public BowlingGame() {
        normalFrames = new NormalFrames();
        lastFrame = new LastFrame();
    }

    public void record(DownedPin downedPin) {
        if (normalFrames.isEnd()) {
            lastFrame.record(downedPin);
            return;
        }

        normalFrames.record(downedPin);
    }

    public boolean isEnd() {
        return normalFrames.isEnd() && lastFrame.isEnded();
    }
}
