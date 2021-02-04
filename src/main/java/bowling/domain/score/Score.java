package bowling.domain.score;

import bowling.bowlingexception.InvalidLeftChanceException;

import java.util.Objects;

public class Score {

    public static final int UNDEFINED = -1;
    private static final int MINIMUM_COUNTS_OF_BONUS = 0;
    private static final int MAXIMUM_COUNTS_OF_BONUS = 2;

    private final int currentScore;
    private final int leftChance;

    public Score(int currentScore, int leftChance) {
        validateChance(leftChance);

        this.currentScore = currentScore;
        this.leftChance = leftChance;
    }

    private void validateChance(int leftChance) {
        if (leftChance < MINIMUM_COUNTS_OF_BONUS || leftChance > MAXIMUM_COUNTS_OF_BONUS) {
            throw new InvalidLeftChanceException();
        }
    }

    public Score addScore(int pitch) {
        return new Score(currentScore + pitch, leftChance - 1);
    }

    public boolean isFixed() {
        return leftChance == 0;
    }

    public int calculateScore() {
        if (!isFixed()) {
            return UNDEFINED;
        }

        return currentScore;
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
