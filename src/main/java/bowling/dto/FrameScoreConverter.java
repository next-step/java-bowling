package bowling.dto;

import bowling.FrameScore;
import bowling.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static bowling.Pins.MAX_PIN_COUNT;
import static bowling.Pins.MIN_PIN_COUNT;

public class FrameScoreConverter {
    private static final String SCORE_DELIMITER = "|";
    private static final String SCORE_STRIKE = "X";
    private static final String SCORE_SPARE = "/";
    private static final String SCORE_GUTTER = "-";

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
        if ((!Objects.isNull(preScore) && !preScore.isEqualsTo(MIN_PIN_COUNT)) && Score.sum(Arrays.asList(preScore, nowScore)) == MAX_PIN_COUNT) {
            return SCORE_SPARE;
        }

        if (nowScore.isEqualsTo(MAX_PIN_COUNT)) {
            return SCORE_STRIKE;
        }

        if (nowScore.isEqualsTo(MIN_PIN_COUNT)) {
            return SCORE_GUTTER;
        }

        return Integer.toString(nowScore.getScore());
    }
}
