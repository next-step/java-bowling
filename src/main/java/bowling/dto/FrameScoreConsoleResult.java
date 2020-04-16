package bowling.dto;

import bowling.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FrameScoreConsoleResult {

    private static final String SCORE_DELIMITER = "|";

    private final String scoreResult;
    private final int subTotal;

    private FrameScoreConsoleResult(final String scoreResult, final int subTotal) {
        this.scoreResult = scoreResult;
        this.subTotal = subTotal;
    }

    public static FrameScoreConsoleResult newInstance(final FrameScore frameScore, final SubTotal subTotal) {
        return new FrameScoreConsoleResult(joinFrameScoreString(frameScore), subTotal.getSubTotalScore());
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

}
