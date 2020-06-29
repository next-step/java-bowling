package bowling.model;

public class BowlingGame {

    private final Player player;
    private final BowlingFrames bowlingFrames;

    private BowlingGame(final Player player) {
        this.player = player;
        this.bowlingFrames = new BowlingFrames();
    }

    public static BowlingGame newInstance(final Player player) {
        return new BowlingGame(player);
    }

    public Player getPlayer() {
        return player;
    }

    public BowlingFrames getBowlingFrames() {
        return bowlingFrames;
    }

    public boolean isOver() {
        return bowlingFrames.isAllFrameOver();
    }

    public int getFrameCount() {
        return bowlingFrames.size();
    }

    public void bowl(final int pinCount) {
        bowlingFrames.bowl(pinCount);
    }
}
