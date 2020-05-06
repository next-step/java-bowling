package bowling.domain.shot;

import java.util.Objects;

public class Pins {
    private static final int MAX = 10;
    private static final int MIN = 0;

    private final int score;

    private Pins(int score) {
        this.score = score;
    }

    public static Pins of(int score) {
        if (score < MIN || MAX < score) {
            throw new IllegalArgumentException(String.format("create Pins fail, score must be %d~%d : score = %d", MIN, MAX, score));
        }

        return new Pins(score);
    }

    public boolean isMax() {
        return this.score == MAX;
    }

    public boolean isMin() {
        return this.score == MIN;
    }

    public Pins getLeftScore() {
        return Pins.of(MAX - score);
    }

    public boolean range(int includeMin, int excludeMax) {
        return includeMin <= score && score < excludeMax;
    }

    public int score() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins1 = (Pins) o;
        return score == pins1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public String toString() {
        return "Score{" +
                score +
                '}';
    }
}
