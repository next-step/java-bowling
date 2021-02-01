package bowling.domain.score;

import java.util.Objects;

public class Score {

    private final int currentScore;
    private final int leftChance;

    public Score(int currentScore, int leftChance) {
        this.currentScore = currentScore;
        this.leftChance = leftChance;
    }

    public Score addScore(int pitch) {
        return new Score(currentScore + pitch, leftChance - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return currentScore == score.currentScore && leftChance == score.leftChance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentScore, leftChance);
    }
}
