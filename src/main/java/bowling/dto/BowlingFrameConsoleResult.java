package bowling.dto;

import bowling.Score;
import bowling.frame.BowlingFrame;
import bowling.framestate.State;

public class BowlingFrameConsoleResult {

    private static final String SCORE_DELIMITER = "|";

    private final String frameScoreResult;
    private final String totalScore;

    private BowlingFrameConsoleResult(final String frameScoreResult, final String totalScore) {
        this.frameScoreResult = frameScoreResult;
        this.totalScore = totalScore;
    }

    public static BowlingFrameConsoleResult newInstance(final BowlingFrame bowlingFrame) {
        return new BowlingFrameConsoleResult(extractFrameScoreResult(bowlingFrame), extractTotalScore(bowlingFrame));
    }

    private static String extractTotalScore(final BowlingFrame bowlingFrame) {
        Score score = bowlingFrame.getScore();
        return Integer.toString(score.getScore());
    }

    private static String extractFrameScoreResult(final BowlingFrame bowlingFrame) {
        State state = bowlingFrame.getState();
        FrameScoreConsoleResult frameScoreConsoleResult = FrameScoreConsoleResult.of(state);
        return frameScoreConsoleResult.toString(state.getPins());
    }

    public String getFrameScoreResult() {
        return frameScoreResult;
    }

    public String getTotalScore() {
        return totalScore;
    }
}
