package bowling;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Score {

    private final static Map<Integer, Score> cache = new HashMap<>();
    private final static int MIN_PIN_COUNT = 0;
    private final static int MAX_PIN_COUNT = 10;

    private final int score;

    static {
        IntStream.rangeClosed(MIN_PIN_COUNT, MAX_PIN_COUNT)
            .forEach(number -> cache.put(number, new Score(number)));
    }

    private Score(int score) {
        valid(score);

        this.score = score;
    }

    public static Score of(int score) {
        return cache.getOrDefault(score, new Score(score));
    }

    private void valid(int score) {
        if (score < MIN_PIN_COUNT) {
            throw new IllegalArgumentException("볼링점수는 음수가 나올 수 없어요.");
        }

        if (score > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("볼링점수는 10점을 넘길 수 없어요.");
        }
    }
}
