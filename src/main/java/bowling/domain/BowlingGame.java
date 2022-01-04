package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.Collections;
import java.util.List;

public class BowlingGame {

    private final Player player;
    private final Frames frames;

    private BowlingGame(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public static BowlingGame create(Player player) {
        return new BowlingGame(player, Frames.first());
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

