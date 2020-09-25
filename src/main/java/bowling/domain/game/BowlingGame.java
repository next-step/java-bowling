package bowling.domain.game;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

public class BowlingGame {
    private final Frames frames;
    private final Player player;

    public BowlingGame(Player player) {
        this.frames = Frames.createFrames();
        this.player = player;
    }

    public static BowlingGame of(Player player) {
        return new BowlingGame(player);
    }

    public Frames getFrames() {
        return frames;
    }

    public boolean isGameOver() {
        return frames.isOver();
    }

    public void nextFrame() {
        frames.addNextFrame();
    }
}
