package bowling.domain;

import java.util.Objects;

public class Score {
    private static final int STRIKE_OR_SPARE = 10;
    private final int score;
    private final int remainingNumber;

    public Score(int score, int remainingNumber) {
        this.score = score;
        this.remainingNumber = remainingNumber;
    }

    public Score bowl(int countOfPins) {
        return new Score(this.score + countOfPins, this.remainingNumber - 1);
    }

    public static Score ofStrike() {
        return new Score(STRIKE_OR_SPARE, 2);
    }

    public static Score ofSpare() {
        return new Score(STRIKE_OR_SPARE, 1);
    }

    public static Score ofGutter() {
        return new Score(0, 0);
    }

    public int getScore() {
        return score;
    }

    public int getRemainingNumber() {
        return remainingNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;
        Score score1 = (Score) o;
        return score == score1.score && remainingNumber == score1.remainingNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, remainingNumber);
    }
}
