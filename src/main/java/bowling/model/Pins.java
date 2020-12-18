package bowling.model;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pins implements Comparable<Pins> {
    private static final String SCORE_ERROR = "허용할 수 없는 값입니다.";

    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 10;

    private static final Map<Integer, Pins> cache;
    private final int score;

    static {
        cache = IntStream.rangeClosed(MIN_SCORE, MAX_SCORE)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), Pins::new));
    }

    private Pins(int fallenPin) {
        this.score = fallenPin;
    }

    public int getScore() {
        return score;
    }

    public static Pins from(int fallenPin) {
        validScore(fallenPin);
        return cache.get(fallenPin);
    }

    public Pins add(Pins pins) {
        int totalScore = this.score + pins.score;
        validScore(totalScore);

        return cache.get(totalScore);
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
    public int compareTo(Pins o) {
        return Integer.compare(this.score, o.score);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pins) {
            return this.score == ((Pins) obj).score;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
