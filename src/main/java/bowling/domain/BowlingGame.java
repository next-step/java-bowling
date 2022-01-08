package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.Collections;
import java.util.List;

public class BowlingGame {

    private final Player player;
    private final Frames frames;

    public BowlingGame(Player player) {
        this.player = player;
        this.frames = Frames.first();
    }

    public void bowl(Pins pins) {
        frames.bowl(pins);
    }

    public String getPlayerName() {
        return player.getName();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames.getFrames());
    }

    public boolean hasNextPitching() {
        return frames.hasNextPitching();
    }

    public int getCurrentFrameIndex() {
        return frames.lastFrameIndex();
    }
}

