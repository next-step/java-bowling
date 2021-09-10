package bowling.model;

import bowling.model.frame.Frame;
import bowling.model.frame.FrameNumber;
import bowling.model.frame.Frames;
import bowling.model.player.Player;

import java.util.List;

public class BowlingGame {
    private final Player player;
    private final Frames frames;

    public BowlingGame(String playerName) {
        player = new Player(playerName);
        frames = new Frames();
    }

    public List<Frame> play(int score) {
        return frames.play(score);
    }

    public boolean canPlayNext() {
        return frames.canPlayNext();
    }

    public FrameNumber nextFrameNumber() {
        return frames.nextFrameNumber();
    }
}
