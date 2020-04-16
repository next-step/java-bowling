package bowling.dto;

import bowling.*;

import java.util.ArrayList;
import java.util.List;

public class FrameScoreConsoleResult {

    private static final String SCORE_DELIMITER = "|";

    private final String scoreResult;
    private final SubTotal subTotal;

    private FrameScoreConsoleResult(final String scoreResult, final SubTotal subTotal) {
        this.scoreResult = scoreResult;
        this.subTotal = subTotal;
    }

    public static FrameScoreConsoleResult newInstance(final BowlingFrame bowlingFrame, final NextAddingUpScores nextAddingUpScores) {
        return new FrameScoreConsoleResult(joinFrameScoreString(bowlingFrame.getFrameScore()), bowlingFrame.getSubTotal(nextAddingUpScores));
    }

    public static String joinFrameScoreString(final FrameScore frameScore) {
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

    public NextAddingUpScores getNextAddingUpScores() {
        return subTotal.getNextAddingUpScores();
    }

    public String getSubTotalScore() {
        if (hasToEmptyDisplay()) {
            return "";
        }

        return Integer.toString(subTotal.getSubTotalScore());
    }

    private boolean hasToEmptyDisplay() {
        return false;
    }
}
