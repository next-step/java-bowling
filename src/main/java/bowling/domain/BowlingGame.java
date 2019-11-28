package bowling.domain;

public class BowlingGame {

    private GameRecord gameRecord;

    public BowlingGame(GameRecord gameRecord) {
        this.gameRecord = gameRecord;
    }

    public void bowl(int score) {
        gameRecord.recordFrame(score);
    }
}
