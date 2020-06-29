package bowling.domain.score;

import java.util.Arrays;
import java.util.function.BiFunction;

import static bowling.domain.score.Score.MAX_SCORE;
import static bowling.domain.score.Score.MIN_SCORE;

public enum Result {

    STRIKE  ((first, second) -> first.equals(MAX_SCORE) && second.equals(MIN_SCORE)),
    SPARE   ((first, second) -> !first.equals(MAX_SCORE) && first.add(second).equals(MAX_SCORE)),
    MISS    ((first, second) -> first.add(second).isBetween(MIN_SCORE, MAX_SCORE)),
    GUTTER  ((first, second) -> first.equals(MIN_SCORE) && second.equals(MIN_SCORE));

    private final BiFunction<Score, Score, Boolean> isMatchFunction;

    Result(BiFunction<Score, Score, Boolean> isMatchFunction) {
        this.isMatchFunction = isMatchFunction;
    }

    public static Result findByFrameScore(Score first, Score second) {
        return Arrays.stream(values())
                .filter(result -> result.isMatchFunction.apply(first, second))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("결과 확인이 불가능한 점수입니다 : %d,%d", first.getContent(), second.getContent()))
                );
    }
}
