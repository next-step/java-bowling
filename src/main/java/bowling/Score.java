package bowling;

import java.util.Arrays;

import static bowling.Pin.MAX_PIN_COUNT;
import static bowling.Pin.MIN_PIN_COUNT;

public class Score {

    private final int score;

    private Score(final int score) {
        validateScoreRange(score);
        this.score = score;
    }

    private static void validateScoreRange(final int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score must be greater than zero");
        }
    }

    public static Score ofAllPins() {
        return of(MAX_PIN_COUNT);
    }

    public static Score ofZeroPins() {
        return of(MIN_PIN_COUNT);
    }

    public static Score of(final int score) {
        return new Score(score);
    }

    public static Score of(final Score... scores) {
        int sum = Arrays.stream(scores)
                .mapToInt(score -> score.score)
                .sum();

        return of(sum);
    }

    public Score add(final int score) {
        return new Score(this.score + score);
    }

    public Score add(final Score score) {
        return add(score.score);
    }

    public int getScore() {
        return score;
    }
}
