package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.domain.score.Score;

public class Board {

    private final Player player;
    private final Frames frames;

    private Board(Player player, Frames frames) {
        this.player = player;
        this.frames = frames;
    }

    public static Board of(Player player, Frames frames) {
        return new Board(player, frames);
    }

    public boolean isAllFrameFinished() {
        return frames.isAllFrameFinished();
    }

    public Player getPlayer() {
        return player;
    }

    public Frames getFrames() {
        return frames;
    }

    public void addScore(Score score) {
        frames.addScore(score);
    }

    public int getCurrentFrameIndex() {
        return frames.getCurrentFrameIndex().orElse(0);
    }
}
