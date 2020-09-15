package bowling.constant;

import bowling.domain.BowlingScore;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public enum ScoringStatus {

    SPARE(StatusPredicates.SPARE, StatusPrintFunction.SPARE),
    STRIKE(StatusPredicates.STRIKE, StatusPrintFunction.STRIKE),
    NONE(StatusPredicates.NONE, StatusPrintFunction.NONE),
    EMPTY(StatusPredicates.EMPTY, StatusPrintFunction.EMPTY);

    private BiPredicate<BowlingScore, BowlingScore> statusPredicate;
    private BiFunction<BowlingScore, BowlingScore, String> printFunction;

    ScoringStatus(BiPredicate<BowlingScore, BowlingScore> statusPredicate,
                  BiFunction<BowlingScore, BowlingScore, String> printFunction) {
        this.statusPredicate = statusPredicate;
        this.printFunction = printFunction;
    }

    public static ScoringStatus findScoringStatus(BowlingScore bowlingScore, BowlingScore bowlingScore2) {
        return Arrays.stream(ScoringStatus.values())
                .filter(scoringStatus -> scoringStatus.statusPredicate.test(bowlingScore, bowlingScore2))
                .findAny()
                .orElse(NONE);
    }

    public String printScore(BowlingScore bowlingScore, BowlingScore bowlingScore2) {
        return printFunction.apply(bowlingScore, bowlingScore2);
    }

    private static class StatusPredicates {
        static final BiPredicate<BowlingScore, BowlingScore> SPARE = (first, second) -> !first.isPerfect() && first.add(second).isPerfect();
        static final BiPredicate<BowlingScore, BowlingScore> STRIKE = (first, second) -> first.isPerfect();
        static final BiPredicate<BowlingScore, BowlingScore> NONE = (first, second) -> false;
        static final BiPredicate<BowlingScore, BowlingScore> EMPTY = (first, second) -> first.isEmpty() && second.isEmpty();
    }

    private static class StatusPrintFunction {
        static final String SCORE_DELIMITER = " | ";
        static final BiFunction<BowlingScore, BowlingScore, String> SPARE = (first, second) ->
                first.printableScore() + SCORE_DELIMITER + "/";
        static final BiFunction<BowlingScore, BowlingScore, String> STRIKE = (first, second) -> "X";
        static final BiFunction<BowlingScore, BowlingScore, String> NONE = (first, second) -> {
            if (second.isEmpty()) {
                return first.printableScore();
            }
            return first.printableScore() + SCORE_DELIMITER + second.printableScore();
        };
        static final BiFunction<BowlingScore, BowlingScore, String> EMPTY = (first, second) -> "";
    }
}
