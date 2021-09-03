package bowling.domain.player;

import bowling.domain.frame.Frames;
import bowling.domain.pins.Pins;
import bowling.domain.score.ScoreResult;

public class BowlingPlayerBoard {

    private final Player player;
    private final Frames frames;
    private final ScoreResult scoreResult;

    private BowlingPlayerBoard(String name) {
        this.player = Player.of(name);
        this.frames = Frames.of();
        this.scoreResult = ScoreResult.of();
    }

    public static BowlingPlayerBoard of(String name) {
        return new BowlingPlayerBoard(name);
    }

    public void bowl(Pins pins) {
        frames.bowl(pins);
    }

    public boolean isTurnOver() {
        return frames.isTurnOver();
    }

    public boolean isFinish() {
        return frames.isFinish();
    }

    public String getPlayerName() {
        return player.getName();
    }

    public Frames getFrames() {
        return frames;
    }

    public ScoreResult getScoreResult() {
        return scoreResult;
    }
}
