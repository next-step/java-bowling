package bowling.domain;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.frame.LastFrame;
import bowling.frame.ShootScore;
import bowling.score.ScoreBoard;
import bowling.score.Scores;

public class BowlingGame {

    private static final int MAX_ROUND = 10;

    private final PlayerName playerName;
    private final Frames frames;
    private final Scores scores;
    private final ScoreBoard scoreBoard;

    public BowlingGame(PlayerName playerName, Frames frames, Scores scores, ScoreBoard scoreBoard) {
        this.playerName = playerName;
        this.frames = frames;
        this.scores = scores;
        this.scoreBoard = scoreBoard;
    }

    public static BowlingGame from(PlayerName playerName, Frames frames, Scores scores, ScoreBoard scoreBoard) {
        return new BowlingGame(playerName, frames, scores, scoreBoard);
    }

    public boolean isEnd() {
        return frames.currentRound() == MAX_ROUND;
    }

    public int currentRound() {
        return frames.currentRound();
    }

    public void shoot(ShootScore shootScore) {
        Frame nextFrame = frames.shoot(shootScore);

        scores.calculateBonusScore(shootScore, scoreBoard);

        if (nextFrame.isEnd() && beforeMaxRound()) {
            scores.addScore(nextFrame.findMyStatus(), scoreBoard);
            frames.goNextRound();
            return;
        }

        if (nextFrame.isEnd() && isMaxRound()) {
            scoreBoard.lastBonusScore((LastFrame) nextFrame);
            frames.goNextRound();
        }
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

    public ScoreBoard scoreBoard() {
        return scoreBoard;
    }
}