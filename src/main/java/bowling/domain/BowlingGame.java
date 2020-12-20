package bowling.domain;

import bowling.dto.BowlingGameDTO;

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

    public BowlingGameDTO exportData() {
        return new BowlingGameDTO(normalFrames, lastFrame);
    }
}
