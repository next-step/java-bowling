package bowling.score.rollling;

import java.util.List;
import java.util.stream.IntStream;

import static bowling.score.rollling.Pin.MAX_PIN_NUMBER;
import static bowling.score.rollling.Pin.MIN_PIN_NUMBER;
import static java.util.stream.Collectors.toList;

public class Rolling {
    private static final String SCORE_RANGE_EXCEPTION = "점수는 10 점 이상이 될 수 없습니다.";
    private static final List<Rolling> ROLLING_SCORE_LIST = IntStream.rangeClosed(MIN_PIN_NUMBER, MAX_PIN_NUMBER).boxed()
            .map(Rolling::new).collect(toList());
    private int score;

    private Rolling(int score) {
        this.score = score;
    }

    public static Rolling of(int score) {
        try {
            return ROLLING_SCORE_LIST.get(score);
        } catch (Exception e) {
            throw new IllegalArgumentException(SCORE_RANGE_EXCEPTION);
        }
    }

    public int getScore() {
        return this.score;
    }
}
