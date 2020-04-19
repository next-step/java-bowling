package bowling.dto;

import bowling.FrameScore;
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

    public static BowlingFrameConsoleResult newInstance(final BowlingFrame bowlingFrame, final int totalScore) {
        return new BowlingFrameConsoleResult(extractFrameScoreResult(bowlingFrame), setTotalScore(bowlingFrame, totalScore));
    }

    private static String setTotalScore(final BowlingFrame bowlingFrame, final int beforeTotalScore) {
        FrameScore frameScore = bowlingFrame.getState().createFrameScore();
        if (bowlingFrame.canCalculateScore()) {
            return String.valueOf(extractTotalScore(bowlingFrame, beforeTotalScore));
        }

        return "";
    }

    private static int extractTotalScore(final BowlingFrame bowlingFrame, final int beforeTotalScore) {
        Score score = bowlingFrame.getFrameScore();
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

    public String getTotalScore() {
        return totalScore;
    }
}
