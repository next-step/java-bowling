package bowling.domain.score;

import java.util.Arrays;
import java.util.function.BiFunction;

import static bowling.domain.score.Score.MAX_SCORE;
import static bowling.domain.score.Score.MIN_SCORE;

public enum Result {

    STRIKE  ((first, second)        -> first.equals(MAX_SCORE) && second.equals(MIN_SCORE),
            (score, nextFrameScore) -> score.add(nextFrameScore.calculateTotalScore())),

    SPARE   ((first, second)        -> !first.equals(MAX_SCORE) && first.add(second).equals(MAX_SCORE),
            (score, nextFrameScore) -> nextFrameScore.getFirst().map(s -> s.add(score)).orElse(score)),

    MISS    ((first, second)        -> first.add(second).isBetween(MIN_SCORE, MAX_SCORE),
            (score, nextFrameScore) -> score),

    GUTTER  ((first, second)        -> first.equals(MIN_SCORE) && second.equals(MIN_SCORE),
            (score, nextFrameScore) -> score);

    private final BiFunction<Score, Score, Boolean> isMatchFunction;
    private final BiFunction<Score, FrameScore, Score> calculateTotalScoreFunction;

    Result(BiFunction<Score, Score, Boolean> isMatchFunction,
           BiFunction<Score, FrameScore, Score> calculateTotalScoreFunction) {
        this.isMatchFunction = isMatchFunction;
        this.calculateTotalScoreFunction = calculateTotalScoreFunction;
    }

    public static Result findByFrameScore(Score first, Score second) {
        return Arrays.stream(values())
                .filter(result -> result.isMatch(first, second))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("결과 확인이 불가능한 점수입니다 : %d,%d", first.getContent(), second.getContent()))
                );
    }

    boolean isMatch(Score first, Score second) {
        return isMatchFunction.apply(first, second);
    }

    public Score calculateTotalScore(Score score, FrameScore nextFrameScore) {
        return calculateTotalScoreFunction.apply(score, nextFrameScore);
    }
}
