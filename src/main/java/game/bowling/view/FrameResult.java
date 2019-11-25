package game.bowling.view;

import game.bowling.domain.Score;

import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * Created by yusik on 2019/11/25.
 */
public enum FrameResult {

    DOUBLE_STRIKE(
            (first, second) -> "X|X",
            (first, second) -> isClearPin(first) && isClearPin(second)
    ),

    STRIKE(
            (first, second) -> "X",
            (first, second) -> isClearPin(first)
    ),

    SPARE(
            (first, second) -> String.format("%d|/", first),
            (first, second) -> first < 10 && isClearPin(first + second)
    ),

    MISS(
            (first, second) -> String.format("%d|%d", first, second),
            (first, second) -> first + second < 10 && first + second > 0
    ),

    GUTTER(
            (first, second) -> "-|-",
            (first, second) -> first + second == 0
    ),

    ;

    private final BiFunction<Integer, Integer, String> function;
    private final ResultPredicate predicate;

    FrameResult(BiFunction<Integer, Integer, String> function, ResultPredicate predicate) {
        this.function = function;
        this.predicate = predicate;
    }

    public static String getFormat(Score score) {
        return Arrays.stream(values())
                .filter(frameResult -> frameResult.isMatch(score.getFirstScore(), score.getSecondScore()))
                .findFirst()
                .map(frameResult -> frameResult.function.apply(score.getFirstScore(), score.getSecondScore()))
                .orElse("");
    }

    private boolean isMatch(int first, int second) {
        return predicate.test(first, second);
    }

    private static boolean isClearPin(Integer numberOfPin) {
        return numberOfPin == 10;
    }
}
