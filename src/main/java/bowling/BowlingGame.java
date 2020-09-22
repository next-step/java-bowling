package bowling;

import java.util.List;

public class BowlingGame {

    private final ScoreBoard scoreBoard;
    private final int entry;

    private BowlingGame(ScoreBoard scoreBoard, int entry) {
        this.scoreBoard = scoreBoard;
        this.entry = entry;
    }

    public static BowlingGame of(int totalFrames, String name, int entry) {
        ScoreBoard scoreBoard = ScoreBoard.of(Player.of(name), totalFrames);
        return new BowlingGame(scoreBoard, entry);
    }

    public int getEntry() {
        return this.entry;
    }

    public String getPlayerName() {
        return scoreBoard.getPlayerName();
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

    public boolean hasNextFrameAndIsCurrentFrameFinished() {
        return scoreBoard.hasNextFrameAndIsCurrentFrameFinished();
    }

    public void shiftCurrentFrame() {
        scoreBoard.shiftCurrentFrame();
    }
}
