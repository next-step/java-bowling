package domain;

public class BowlingGame {

    private Player player;

    private BowlingGame(Player player) {
        this.player = player;
    }

    public static BowlingGame from(Player player) {
        return new BowlingGame(player);
    }
}
