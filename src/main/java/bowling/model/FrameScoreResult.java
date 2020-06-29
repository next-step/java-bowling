package bowling.model;

import static bowling.model.Pins.MAX_PIN_COUNT;
import static bowling.model.Pins.MIN_PIN_COUNT;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public enum FrameScoreResult {

    STRIKE(score -> "X"),
    SPARE(score -> "/"),
    MISS(Score::toString),
    GUTTER(score -> "-");

    private static final int FIRST_SCORE_INDEX = 0;
    private static final int SECOND_SCORE_INDEX = 1;

    private final Function<Score, String> printFunction;

    FrameScoreResult(final Function<Score, String> printFunction) {
        this.printFunction = printFunction;
    }

    public static FrameScoreResult of(final Score preScore, final Score nowScore) {
        if (isSpare(preScore, nowScore)) {
            return SPARE;
        }

        if (isStrike(nowScore)) {
            return STRIKE;
        }

        if (isGutter(nowScore)) {
            return GUTTER;
        }

        return MISS;
    }

    public static boolean canBowlOneMore(final List<Score> scores) {
        if (of(null, scores.get(FIRST_SCORE_INDEX)) == STRIKE || of(null, scores.get(SECOND_SCORE_INDEX)) == STRIKE) {
            return true;
        }

        return of(scores.get(FIRST_SCORE_INDEX), scores.get(SECOND_SCORE_INDEX)) == SPARE;
    }

    public static boolean canBowlOneMoreByFirstStrike(final List<Score> scores) {
        return of(null, scores.get(FIRST_SCORE_INDEX)) == STRIKE;
    }

    public String toConsoleResult(final Score score) {
        return printFunction.apply(score);
    }

    private static boolean isSpare(final Score preScore, final Score nowScore) {
        return (!Objects.isNull(preScore)
            && !preScore.isEqualsTo(MIN_PIN_COUNT)
            && Score.sum(Arrays.asList(preScore, nowScore)) == MAX_PIN_COUNT);
    }

    private static boolean isStrike(final Score score) {
        return score.isEqualsTo(MAX_PIN_COUNT);
    }

    private static boolean isGutter(final Score score) {
        return score.isEqualsTo(MIN_PIN_COUNT);
    }
}
