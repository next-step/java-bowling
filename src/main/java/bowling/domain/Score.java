package bowling.domain;

import static bowling.domain.Pins.START_PIN_COUNT;

import java.util.Objects;

public class Score {

    public static final int DEFAULT_SCORE = 0;

    private final int score;

    public Score() {
        this(DEFAULT_SCORE);
    }
    public Score(int score) {
        this.score = score;
    }

    public int get() {
        return this.score;
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

    public boolean isAll() {
        return score == START_PIN_COUNT;
    }

    public boolean isHit(int hitScore) {
        return this.score == hitScore;
    }
}
