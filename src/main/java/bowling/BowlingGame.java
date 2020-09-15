package bowling;

public class BowlingGame {

    private final int totalFrames;
    private final ScoreBoard scoreBoard;

    private BowlingGame(int totalFrames, ScoreBoard scoreBoard) {
        this.totalFrames = totalFrames;
        this.scoreBoard = scoreBoard;
    }

    public int getTotalFrames() {
        return this.totalFrames;
    }

    public String getPlayerName() {
        return scoreBoard.getPlayerName();
    }

    public int getCurrentFrame() {
        return scoreBoard.getCurrentFrame();
    }

    public void bowl(int falledPins) {
        scoreBoard.bowl(falledPins);
    }

    public static BowlingGame of(int totalFrames, String name) {
        ScoreBoard scoreBoard = ScoreBoard.of(Player.of(name), totalFrames);
        return new BowlingGame(totalFrames, scoreBoard);
    }
}
