package bowling.dto;

import bowling.Score;
import bowling.frame.BowlingFrame;
import bowling.framestate.State;

public class BowlingFrameConsoleResult {

    private static final String SCORE_DELIMITER = "|";

    private final String frameScoreResult;
    private final int totalScore;

    private BowlingFrameConsoleResult(final String frameScoreResult, final int totalScore) {
        this.frameScoreResult = frameScoreResult;
        this.totalScore = totalScore;
    }

    public static BowlingFrameConsoleResult newInstance(final BowlingFrame bowlingFrame, final int beforeTotalScore) {
        return new BowlingFrameConsoleResult(extractFrameScoreResult(bowlingFrame), extractTotalScore(bowlingFrame, beforeTotalScore));
    }

    private static int extractTotalScore(final BowlingFrame bowlingFrame, final int beforeTotalScore) {
        Score score = bowlingFrame.getScore();
        return beforeTotalScore + score.getScore();
    }

    private static String extractFrameScoreResult(final BowlingFrame bowlingFrame) {
        State state = bowlingFrame.getState();
        FrameScoreConsoleResult frameScoreConsoleResult = FrameScoreConsoleResult.of(state);
        return frameScoreConsoleResult.toString(state.getPins());
    }

    public String getFrameScoreResult() {
        return frameScoreResult;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
