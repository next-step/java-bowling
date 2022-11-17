package bowling.domain;

import java.util.List;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class BowlingGame {
    private final Player player;
    private final Frames frames;

    public BowlingGame(Player player) {
        this.player = player;
        this.frames = Frames.init();
    }

    public boolean bowl(int falledPins) {
        frames.bowl(falledPins);
        boolean isCurrentFrameEnded = frames.isCurrentFrameEnded();

        if (isCurrentFrameEnded && isGamePlayable()) {
            frames.createNextFrame();
        }

        return isCurrentFrameEnded;
    }

    public boolean isGamePlayable() {
        return frames.isGamePlayable();
    }

    public int getCurrentFrameNumber() {
        return frames.getCurrentFrameNumber();
    }

    public List<Frame> getFrames() {
        return frames.getValue();
    }

    public String getPlayerName() {
        return player.getName();
    }
}
