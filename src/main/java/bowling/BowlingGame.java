package bowling;

public class BowlingGame {

    private final Player player;
    private final BowlingFrames bowlingFrames;

    private BowlingGame(final Player player) {
        this.player = player;
        this.bowlingFrames = new BowlingFrames();
    }

    public static BowlingGame newInstance(final String name) {
        return new BowlingGame(Player.of(name));
    }

    public static BowlingGame newInstance(final Player player) {
        return new BowlingGame(player);
    }

    public void bowl(final int pinCount) {
        if (isOver()) {
            throw new RuntimeException("The bowling Game is Over.");
        }

        bowlingFrames.bowl(pinCount);
    }

    public boolean isOver() {
        return bowlingFrames.isAllFrameOver();
    }

    public int getFrameCount() {
        return bowlingFrames.count();
    }

    public Player getPlayer() {
        return player;
    }

    public BowlingFrames getBowlingFrames() {
        return bowlingFrames;
    }
}
