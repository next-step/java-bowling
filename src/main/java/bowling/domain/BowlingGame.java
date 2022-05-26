package bowling.domain;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.frame.ShootScore;
import bowling.score.Scores;

public class BowlingGame {

    private static final int MAX_ROUND = 10;

    private final PlayerName playerName;
    private final Frames frames;
    private final Scores scores;

    public BowlingGame(PlayerName playerName, Frames frames, Scores scores) {
        this.playerName = playerName;
        this.frames = frames;
        this.scores = scores;
    }

    public static BowlingGame from(PlayerName playerName, Frames frames, Scores scores) {
        return new BowlingGame(playerName, frames, scores);
    }

    public boolean isEnd() {
        return frames.currentRound() == MAX_ROUND;
    }

    public int currentRound() {
        return frames.currentRound();
    }

    public void shoot(ShootScore shootScore) {
        Frame nextFrame = frames.shoot(shootScore);

        scores.calculateBonusScore(shootScore);

        if (nextFrame.isEnd()) {
            scores.addScore(nextFrame.findMyStatus());
            frames.goNextRound();
        }
    }

    public String playerName() {
        return playerName.playerName();
    }

    public Frames frames() {
        return frames;
    }
}