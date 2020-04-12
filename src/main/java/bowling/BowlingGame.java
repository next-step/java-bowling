package bowling;

public class BowlingGame {

    private final Player player;
    private final BowlingFrames bowlingFrames;

    private BowlingGame(final Player player) {
        this.player = player;
        this.bowlingFrames = new BowlingFrames();
    }

    public static BowlingGame newInstance(final String name) {
        return new BowlingGame(Player.newInstance(name));
    }

    public void bowl(final int pinCount) {
        if (isOver()) {
            throw new RuntimeException("The bowling Game is Over.");
        }

        bowlingFrames.bowl(pinCount);
    }

    private boolean isOver() {
        return bowlingFrames.isAllFrameOver();
    }
}
