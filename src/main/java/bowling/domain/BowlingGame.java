package bowling.domain;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.frame.LastFrame;
import bowling.frame.ShootScore;
import bowling.score.Score;
import bowling.score.Scores;

import java.util.List;

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

    public Frame shoot(ShootScore shootScore) {
        Frame currentFrame = frames.shoot(shootScore);

        scores.calculateBonusScore(shootScore);

        if (currentFrame.isEnd() && beforeMaxRound()) {
            Score score = currentFrame.findMyStatus().createScore();

            scores.addScore(score);
            frames.goNextRound();
            return currentFrame;
        }

        if (currentFrame.isEnd() && isMaxRound()) {
            scores.lastBonusScore((LastFrame) currentFrame);
            frames.goNextRound();
        }
        return currentFrame;
    }

    private boolean beforeMaxRound() {
        return currentRound() < MAX_ROUND - 1;
    }

    private boolean isMaxRound() {
        return currentRound() == MAX_ROUND - 1;
    }

    public String playerName() {
        return playerName.playerName();
    }

    public Frames frames() {
        return frames;
    }

    public List<Integer> totalScores() {
        return scores.scoreBoard();
    }
}