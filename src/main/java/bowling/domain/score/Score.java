package bowling.domain.score;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Score {
    public static final int SCORE_MIN = 0;
    public static final int SCORE_MAX = 10;
    private static final String OUT_OF_SCORE_EXCEPTION_STATEMENT = "점수값이 범위를 벗어났습니다";

    private static final Map<Integer, Score> SCORES =
        IntStream.rangeClosed(SCORE_MIN, SCORE_MAX)
            .mapToObj(Score::new)
            .collect(Collectors.toMap(Score::score, Function.identity()));
    private final int score;

    private Score(int score) {
        validate(score);
        this.score = score;
    }

    public static Score from(int score) {
        validate(score);
        return SCORES.get(score);
    }

    public static Score from(Score scoreObject) {
        return new Score(scoreObject.score);
    }

    private static void validate(int score) {
        if (score < SCORE_MIN || SCORE_MAX < score) {
            throw new IllegalArgumentException(OUT_OF_SCORE_EXCEPTION_STATEMENT);
        }
    }

    public boolean isLessThanTenAfterAdd(Score scoreObject) {
        if (Objects.isNull(scoreObject)) {
            return this.score < 10;
        }
        return (this.score + scoreObject.score) < 10;
    }

    public boolean isOverTenAfterAdd(Score scoreObject) {
        if (Objects.isNull(scoreObject)) {
            return this.score > 10;
        }
        return (this.score + scoreObject.score) > 10;
    }

    public boolean isEqualTenAfterAdd(Score scoreObject) {
        if (Objects.isNull(scoreObject)) {
            return this.score == 10;
        }
        return (this.score + scoreObject.score) == 10;
    }

    public int score() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
