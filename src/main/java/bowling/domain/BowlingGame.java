package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;

import java.util.List;

public final class BowlingGame {

    private final Player player;
    private final Frames frames;

    public BowlingGame(final String playerName) {
        this.player = Player.valueOf(playerName);
        this.frames = Frames.generate();
    }

    public void input(final int score) {
        frames.add(score);
    }

    public int nextRoundNumber() {
        return frames.nextTurnRoundNumber();
    }

    public boolean isFinished() {
        return frames.isFinished();
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    public Player getPlayer() {
        return player;
    }
}
