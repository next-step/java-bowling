package bowling.domain.score;

import java.util.Arrays;

import static bowling.domain.score.Score.MAX_SCORE;
import static bowling.domain.score.Score.MIN_SCORE;

public enum Result {

    STRIKE {
        @Override
        boolean isMatch(Score first, Score second) {
            return first.equals(MAX_SCORE) && second.equals(MIN_SCORE);
        }
    },
    SPARE {
        @Override
        boolean isMatch(Score first, Score second) {
            return !first.equals(MAX_SCORE) && first.add(second).equals(MAX_SCORE);
        }
    },
    MISS {
        @Override
        boolean isMatch(Score first, Score second) {
            Score total = first.add(second);
            return total.isGreaterThan(MIN_SCORE) && MAX_SCORE.isGreaterThan(total);
        }
    },
    GUTTER {
        @Override
        boolean isMatch(Score first, Score second) {
            return first.equals(MIN_SCORE) && second.equals(MIN_SCORE);
        }
    };

    public static Result findByScores(Score first, Score second) {
        return Arrays.stream(values())
                .filter(result -> result.isMatch(first, second))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("결과 확인이 불가능한 점수입니다 : %d,%d", first.getContent(), second.getContent()))
                );
    }

    abstract boolean isMatch(Score first, Score second);
}
