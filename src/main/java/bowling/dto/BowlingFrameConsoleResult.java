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

    public static BowlingFrameConsoleResult newInstance(final BowlingFrame bowlingFrame, final Score totalScore) {
        return new BowlingFrameConsoleResult(extractFrameScoreResult(bowlingFrame), makeTotalScore(bowlingFrame, totalScore));
    }

    private static String makeTotalScore(final BowlingFrame bowlingFrame, final Score totalScore) {
        if (bowlingFrame.canCalculateScore()) {
            return String.valueOf(totalScore.getScore());
        }

        return "";
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
