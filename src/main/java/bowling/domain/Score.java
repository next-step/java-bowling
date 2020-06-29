package bowling.domain;

import bowling.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public class Score {

    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 10;

    private static final Map<Integer, Score> scores = new HashMap<>();

    static {
        IntStream.rangeClosed(MIN_SCORE, MAX_SCORE).forEach(score -> scores.put(score, new Score(score)));
    }

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score of(String score) {
        checkScore(score);
        return of(Integer.parseInt(score.trim()));
    }

    public static Score of(int score) {
        return Optional.ofNullable(scores.get(score))
                .orElseThrow(() -> new IllegalArgumentException("점수의 범위를 벗어났습니다."));
    }

    private static void checkScore(String score) {
        if (StringUtils.isEmpty(score)) {
            throw new IllegalArgumentException("점수가 비어있습니다.");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
