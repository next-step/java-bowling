package bowling.dto;

import bowling.FrameScore;
import bowling.FrameScoreResult;
import bowling.Score;
import bowling.SubTotal;

import java.util.ArrayList;
import java.util.List;

public class FrameScoreConsoleResult {

    private static final String SCORE_DELIMITER = "|";

    private final String scoreResult;
    private final String subTotal;

    private FrameScoreConsoleResult(final String scoreResult, final String subTotal) {
        this.scoreResult = scoreResult;
        this.subTotal = subTotal;
    }

    public static FrameScoreConsoleResult newInstance(final FrameScore frameScore, final SubTotal subTotal) {
        return new FrameScoreConsoleResult(joinFrameScoreString(frameScore), Integer.toString(subTotal.getSubTotalScore()));
    }

    public static String joinFrameScoreString(final FrameScore frameScore) {
        List<String> scoresString = new ArrayList<>();
        Score preScore = null;

        for (Score score : frameScore.getScores()) {
            FrameScoreResult frameResult = FrameScoreResult.of(preScore, score);
            ScoreConsoleResult consoleResult = ScoreConsoleResult.of(frameResult);
            scoresString.add(consoleResult.toConsoleResult(score));
            preScore = score;
        }

        return String.join(SCORE_DELIMITER, scoresString);
    }

    public String getScoreResult() {
        return scoreResult;
    }

    public String getSubTotal() {
        return subTotal;
    }
}
