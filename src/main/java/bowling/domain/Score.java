package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static java.lang.String.format;

public class Score {
    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final Map<Integer, Score> SCORE_POOL;

    static {
        Map<Integer, Score> scores = new HashMap<>();
        IntStream.rangeClosed(MIN, MAX)
                .forEach(score -> scores.put(score, new Score(score)));
        SCORE_POOL = scores;
    }

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score of(int score) {
        if (score < MIN || score > MAX) {
            throw new IllegalArgumentException(format("볼링 점수는 %s에서 %s 사이이어야 합니다.", MIN, MAX));
        }
        
        return SCORE_POOL.get(score);
    }

    public boolean isMax() {
        return score == MAX;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;

        Score score1 = (Score) o;

        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return score;
    }
}
