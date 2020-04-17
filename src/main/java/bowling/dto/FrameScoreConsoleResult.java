package bowling.dto;

import bowling.FrameScore;
import bowling.FrameScoreResult;
import bowling.Score;

import java.util.ArrayList;
import java.util.List;

public class FrameScoreConsoleResult {

    private static final String SCORE_DELIMITER = "|";

    private final String scoreResult;
    private final TotalScoreResult totalScoreResult;

    private FrameScoreConsoleResult(final String scoreResult, final TotalScoreResult totalScoreResult) {
        this.scoreResult = scoreResult;
        this.totalScoreResult = totalScoreResult;
    }

    public static FrameScoreConsoleResult newInstance(final FrameScore frameScore, final TotalScoreResult totalScoreResult) {
        return new FrameScoreConsoleResult(joinFrameScoreString(frameScore), totalScoreResult);
    }

    private static String joinFrameScoreString(final FrameScore frameScore) {
        List<String> scores = new ArrayList<>();
        Score preScore = null;

        for (Score score : frameScore.getScores()) {
            FrameScoreResult frameResult = FrameScoreResult.of(preScore, score);
            ScoreConsoleResult consoleResult = ScoreConsoleResult.of(frameResult);

            scores.add(consoleResult.toConsoleResult(score));
            preScore = score;
        }

        return String.join(SCORE_DELIMITER, scores);
    }

    public String getScoreResult() {
        return scoreResult;
    }

    public String getTotalScore() {
        if (totalScoreResult.isCanDisplay()) {
            return String.valueOf(totalScoreResult.getTotalScore());
        }

        return "";
    }
}
