package bowling;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Score {

    private static final Map<Integer, Score> cache = new HashMap<>();

    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 300;

    private final int score;


    static {
        IntStream.rangeClosed(MIN_SCORE, MAX_SCORE)
            .forEach(number -> cache.put(number, new Score(number)));
    }

    private Score(int score) {
        valid(score);

        this.score = score;
    }

    public static Score of(int score) {
        return cache.getOrDefault(score, new Score(score));
    }

    public boolean isStrike() {
        return score == 10;
    }

    public boolean isNonScore() {
        return score == 0;
    }

    public int getScore() {
        return score;
    }

    private void valid(int score) {
        if (score < MIN_SCORE) {
            throw new IllegalArgumentException("점수는 음수가 올 수 없어요");
        }

        if (score > MAX_SCORE) {
            throw new IllegalArgumentException("볼링점수의 최대값을 넘겼어요");
        }

    }
}
