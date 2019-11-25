package game.bowling.domain;

/**
 * Created by yusik on 2019/11/20.
 */
public class Bowling {

    private ScoreBoard scoreBoard;

    public Bowling(String playerName) {
        this.scoreBoard = new ScoreBoard(playerName);
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public boolean isNotFinish() {
        return scoreBoard.hasNextFrame();
    }

    public int nextFrameNo() {
        return scoreBoard.nextFrameNo();
    }

    public void bowl(int score) {
        scoreBoard.bowl(score);
    }
}
