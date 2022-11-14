package bowling.step2.domain;

public class BowlingGame {
    public static final int GAME_START_INDEX = 1;
    public static final int GAME_LAST_INDEX = 10;

    private final Player player;

    public BowlingGame(String name) {
        this.player = new Player(name, GAME_START_INDEX, GAME_LAST_INDEX);
    }

    public void recode(int score) {
        player.addScore(score);
    }

    public Player player() {
        return player;
    }

    public Boolean IsEndOfOneFrame(int i) {
        return player.IsEndOfOneFrame(i);
    }
}
