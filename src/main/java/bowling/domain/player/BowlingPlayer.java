package bowling.domain.player;

import bowling.domain.frame.Frames;
import bowling.domain.score.ScoreResult;

public class BowlingPlayer {

    private final Player player;
    private final Frames frames;
    private final ScoreResult scoreResult;

    private BowlingPlayer(String name) {
        this.player = Player.of(name);
        this.frames = Frames.of();
        this.scoreResult = ScoreResult.of();
    }

    public static BowlingPlayer of(String name) {
        return new BowlingPlayer(name);
    }

    public boolean isTurnOver() {
        return frames.isTurnOver();
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
