package bowling.dto;

import bowling.FrameScore;
import bowling.Score;

import java.util.*;

public class FrameScoreConverter {
    private static final String SCORE_DELIMITER = "|";

    public static String joinFrameScoreString(FrameScore frameScore) {
        List<String> scoresString = new ArrayList<>();
        Score preScore = null;

        for (Score score : frameScore.getScores()) {
            scoresString.add(convertTo(preScore, score));
            preScore = score;
        }

        return String.join(SCORE_DELIMITER, scoresString);
    }

    // TODO: 2020-04-12
    private static String convertTo(final Score preScore, final Score nowScore) {
        if ((!Objects.isNull(preScore) && !preScore.isEqualsTo(0)) && Score.sum(Arrays.asList(preScore, nowScore)) == 10) {
            return "/";
        }

        if (nowScore.isEqualsTo(10)) {
            return "X";
        }

        if (nowScore.isEqualsTo(0)) {
            return "-";
        }

        return Integer.toString(nowScore.getScore());
    }
}
