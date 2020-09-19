package bowling;

import java.util.List;

public class BowlingGame {

    private final ScoreBoard scoreBoard;

    private BowlingGame(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public static BowlingGame of(int totalFrames, String name) {
        ScoreBoard scoreBoard = ScoreBoard.of(Player.of(name), totalFrames);
        return new BowlingGame(scoreBoard);
    }

    public Player getPlayer() {
        return scoreBoard.getPlayer();
    }

    public List<Frame> getFrames() {
        return scoreBoard.getFrames();
    }

    public void bowl(Pin pin) {
        scoreBoard.bowl(pin);
    }

    public boolean isFinished() {
        return scoreBoard.isFinished();
    }
}
