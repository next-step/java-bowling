package bowling.view;

import bowling.model.FrameScore;
import bowling.model.FrameScoreResult;
import bowling.model.Score;
import java.util.ArrayList;
import java.util.List;

public class FrameScoreConsoleResult {

    private static final String SCORE_DELIMITER = "|";

    private final String scoreResult;

    private FrameScoreConsoleResult(final String scoreResult) {
        this.scoreResult = scoreResult;
    }

    public static FrameScoreConsoleResult newInstance(final FrameScore frameScore) {
        return new FrameScoreConsoleResult(joinFrameScoreString(frameScore));
    }

    private static String joinFrameScoreString(final FrameScore frameScore) {
        List<String> scoresString = new ArrayList<>();

        Score preScore = null;
        for(Score score : frameScore.getScores()) {
            FrameScoreResult frameResult = FrameScoreResult.of(preScore, score);
            scoresString.add(frameResult.toConsoleResult(score));
            preScore = score;
        }

        return String.join(SCORE_DELIMITER, scoresString);
    }

    public String getScoreResult() {
        return scoreResult;
    }
}
