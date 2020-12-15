package bowling.model;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Score implements Comparable<Score> {
    private static final String SCORE_ERROR = "허용할 수 없는 값입니다.";
    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 10;

    private static final Map<Integer, Score> cache;
    private final int score;

    static {
        cache = IntStream.rangeClosed(MIN_SCORE, MAX_SCORE)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), Score::new));
    }

    private Score(int fallenPin) {
        this.score = fallenPin;
    }

    public static Score min() {
        return cache.get(MIN_SCORE);
    }

    public static Score from(int fallenPin) {
        validScore(fallenPin);
        return cache.get(fallenPin);
    }

    public Score add(int fallenPin) {
        int totalScore = this.score + fallenPin;
        validScore(totalScore);

        return cache.get(totalScore);
    }

    public Score subtraction(Score score) {
        int subtractionScore = this.score - score.score;
        validScore(subtractionScore);
        return cache.get(subtractionScore);
    }

    public boolean isMaxScore() {
        return score == MAX_SCORE;
    }

    public boolean isMinScore() {
        return score == MIN_SCORE;
    }

    private static void validScore(int fallenPin) {
        if (!cache.containsKey(fallenPin)) {
            throw new IllegalArgumentException(SCORE_ERROR);
        }
    }

    @Override
    public String toString() {
        if (score == MIN_SCORE) {
            return "-";
        }
        return String.valueOf(score);
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(this.score, o.score);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Score){
            return this.score == ((Score) obj).score;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
