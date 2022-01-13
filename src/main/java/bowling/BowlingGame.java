package bowling;

import bowling.frame.Frame;
import bowling.frame.Frames;

import java.util.List;

public class BowlingGame {

    private final Frames frames;
    private final Player player;

    public BowlingGame(Player player) {
        this.frames = Frames.first();
        this.player = player;
    }

    public void bowl(Pins fallenPins) {
        frames.bowl(fallenPins);
    }

    public int recentFrameNo() {
        return frames.recentFrameNo();
    }

    public boolean hasNextBowl() {
        return frames.hasNextBowl();
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    public String getPlayerName() {
        return player.getName();
    }
}
