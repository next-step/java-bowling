package refactor;

import java.util.Objects;

import static bowling.util.Const.RANDOM;

public class Score {
    private static int MAX_SCORE = 10;
    private final int score;
    private final int remaining;

    public Score(int score, int remaining) {
        validate(score);
        this.score = score;
        this.remaining = remaining;
    }

    private void validate(int score) {
        if (score > 10) {
            throw new IllegalArgumentException("score cannot be over 10.");
        }
    }

    public Score() {
        this(0, 2);
    }

    public Score pitchManual(int numberOfPins) {
        return new Score(this.score + numberOfPins, remaining - 1);
    }

    public Score pitch() {
        int numberOfPins = RANDOM.nextInt(MAX_SCORE - this.score + 1);
        return new Score(numberOfPins, this.remaining - 1);
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                ", remaining=" + remaining +
                '}';
    }

    public boolean playing() {
        return this.remaining > 0;
    }

    public int score() {
        if (playing()) {
            throw new UnsupportedOperationException("cannot calculate score yet.");
        }
        return this.score();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && remaining == score1.remaining;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, remaining);
    }
}
