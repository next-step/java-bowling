package bowling;

public class GameStatus {
    private GameService game;

    public GameStatus(GameService game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "|  PJS |      |      |      |      |      |      |      |      |      |      |";
    }
}
