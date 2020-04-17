package bowling;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import static bowling.NextAddingUpScores.MAX_SIZE;
import static bowling.NextAddingUpScores.SPARE_ADDING_UP_COUNT;
import static bowling.Pins.MAX_PIN_COUNT;
import static bowling.Pins.MIN_PIN_COUNT;

public enum FrameScoreResult {
    STRIKE(TotalScore::sumStrike, FrameScoreResult::canSumStrike),
    SPARE(TotalScore::sumSpare, FrameScoreResult::canSumSpare),
    MISS((score, nextAddingUpScores) -> score, nextAddingUpScores -> true),
    GUTTER((score, nextAddingUpScores) -> score, nextAddingUpScores -> true);

    private static final int FIRST_SCORE_INDEX = 0;
    private static final int SECOND_SCORE_INDEX = 1;

    private final BiFunction<TotalScore, NextAddingUpScores, TotalScore> subTotalFunction;
    private final Function<NextAddingUpScores, Boolean> checkGetSubTotalFunction;

    FrameScoreResult(final BiFunction<TotalScore, NextAddingUpScores, TotalScore> subTotalFunction, final Function<NextAddingUpScores, Boolean> checkGetSubTotalFunction) {
        this.subTotalFunction = subTotalFunction;
        this.checkGetSubTotalFunction = checkGetSubTotalFunction;
    }

    public static FrameScoreResult of(final List<Score> scores) {
        if (scores.size() >= 2) {
            return of(scores.get(FIRST_SCORE_INDEX), scores.get(SECOND_SCORE_INDEX));
        }

        if (scores.size() == 1) {
            return of(null, scores.get(FIRST_SCORE_INDEX));
        }

        return GUTTER;
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

    public TotalScore calculateTotalScore(final TotalScore score, final NextAddingUpScores nextAddingUpScores) {
        return subTotalFunction.apply(score, nextAddingUpScores);
    }

    public boolean canCalculateTotalScore(final NextAddingUpScores nextAddingUpScores) {
        return checkGetSubTotalFunction.apply(nextAddingUpScores);
    }

    private static boolean isSpare(final Score preScore, final Score nowScore) {
        return (!Objects.isNull(preScore) && !preScore.isEqualsTo(MIN_PIN_COUNT)) && Score.sum(Arrays.asList(preScore, nowScore)) == MAX_PIN_COUNT;
    }

    private static boolean isStrike(final Score score) {
        return score.isEqualsTo(MAX_PIN_COUNT);
    }

    private static boolean isGutter(final Score score) {
        return score.isEqualsTo(MIN_PIN_COUNT);
    }

    private static boolean canSumStrike(final NextAddingUpScores nextAddingUpScores) {
        return nextAddingUpScores.size() == MAX_SIZE;
    }

    private static boolean canSumSpare(final NextAddingUpScores nextAddingUpScores) {
        return nextAddingUpScores.size() >= SPARE_ADDING_UP_COUNT;
    }

}
