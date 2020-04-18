package bowling.dto;

import bowling.Score;
import bowling.frame.BowlingFrame;
import bowling.framestate.State;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingFrameConsoleResult {

    private static final String SCORE_DELIMITER = "|";

    private final String frameScoreResult;
    private final String totalScore;

    private BowlingFrameConsoleResult(final String frameScoreResult, final String totalScore) {
        this.frameScoreResult = frameScoreResult;
        this.totalScore = totalScore;
    }

    public static BowlingFrameConsoleResult newInstance(final BowlingFrame bowlingFrame) {
        return new BowlingFrameConsoleResult(joinScoreString(bowlingFrame), getTotalScore(bowlingFrame));
    }

    private static String getTotalScore(final BowlingFrame bowlingFrame) {
        Score score = bowlingFrame.getScore();
        return Integer.toString(score.getScore());
    }

    private static String joinScoreString(final BowlingFrame bowlingFrame) {
        State state = bowlingFrame.getState();
        List<String> scoreStrings = state.getPins().stream()
                .map(String::valueOf)
                .collect(Collectors.toList());

        return String.join(SCORE_DELIMITER, scoreStrings);
    }

    public String getFrameScoreResult() {
        return frameScoreResult;
    }

    public String getTotalScore() {
        return totalScore;
    }
}
