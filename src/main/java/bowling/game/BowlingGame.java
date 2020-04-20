package bowling.game;

import bowling.Pin;
import bowling.Player;
import bowling.frame.BowlingFrames;

public class BowlingGame {

    private final Player player;
    private final BowlingFrames bowlingFrames;

    private BowlingGame(final Player player) {
        this.player = player;
        this.bowlingFrames = BowlingFrames.newInstance();
    }

    public static BowlingGame newInstance(final String name) {
        return new BowlingGame(Player.of(name));
    }

    public static BowlingGame newInstance(final Player player) {
        return new BowlingGame(player);
    }

    public boolean isRecentFrameOver() {
        return bowlingFrames.isRecentFrameOver();
    }

    public boolean isAllFramesOver() {
        return bowlingFrames.isAllFrameOver();
    }

    public void bowl(final Pin pinCount) {
        bowlingFrames.bowl(pinCount);
    }

    public int getFrameCount() {
        return bowlingFrames.size();
    }

    public Player getPlayer() {
        return player;
    }

    public BowlingFrames getBowlingFrames() {
        return bowlingFrames;
    }
}
