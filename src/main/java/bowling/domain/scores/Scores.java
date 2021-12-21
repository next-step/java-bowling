package bowling.domain.scores;

import bowling.Score;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class Scores {

    private static final int START_NUMBER = 0;
    private static final int FIRST_FITCH = 1;
    private static final int GENERAL_FRAME_NUMBER = 2;
    private static final int PIN_MAX_NUMBER = 10;

    protected final List<Score> scores;

    public Scores(List<Score> scores) {
        this.scores = scores;
    }

    public abstract Scores add(int hitCount);

    public abstract boolean isClosed();

    public final boolean containStrike() {
        return scores.stream()
            .anyMatch(Score::isStrike);
    }

    public final boolean containSpare() {
        if (scores.size() < GENERAL_FRAME_NUMBER) {
            return false;
        }

        return sumFirstAndSecond() == PIN_MAX_NUMBER;
    }

    public final boolean isMiss() {
        if (scores.size() < GENERAL_FRAME_NUMBER) {
            return false;
        }

        return scores.get(FIRST_FITCH).equals(Score.of(START_NUMBER));
    }

    public final boolean isGutter() {
        return scores.stream()
            .allMatch(Score::isNonScore);
    }


    public final int sumScore() {
        return scores.stream()
            .mapToInt(Score::getScore)
            .sum();
    }

    protected final int sumFirstAndSecond() {
        return IntStream.range(START_NUMBER, GENERAL_FRAME_NUMBER)
            .boxed()
            .map(scores::get)
            .mapToInt(Score::getScore)
            .sum();
    }

    protected static List<Score> toScore(int[] numbers) {
        return Arrays.stream(numbers)
            .boxed()
            .map(Score::of)
            .collect(Collectors.toList());
    }
}
