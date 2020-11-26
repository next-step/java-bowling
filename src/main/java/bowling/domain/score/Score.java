package bowling.domain.score;

import static bowling.domain.score.ScoreType.ORDINARY;
import static bowling.domain.score.ScoreType.STRIKE;

public class Score {

    public static final int MIN_SCORE = 0;
    public static final int MAX_SCORE = 10;
    private final int score;
    private final ScoreType type;

    private Score(int score, ScoreType type) {
        this.score = score;
        this.type = type;
    }

    public static Score ordinary(int score) {
        return of(score, ScoreType.ORDINARY);
    }

    public static Score of(int score, ScoreType type) {
        validateScore(score);
        return new Score(score, type);
    }

    private static void validateScore(int score) {
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new InvalidScoreException();
        }
    }

    public static Score of(int score, boolean isSpare) {
        if (isSpare) {
            return spare(score);
        }
        if (score == MIN_SCORE) {
            return gutter();
        }
        if (score == MAX_SCORE) {
            return strike();
        }
        return ordinary(score);
    }

    public static Score strike() {
        return of(MAX_SCORE, STRIKE);
    }

    public static Score spare(int score) {
        return of(score, ScoreType.SPARE);
    }

    public static Score gutter() {
        return of(0, ScoreType.GUTTER);
    }

    public int getScore() {
        return score;
    }

    public ScoreType getType() {
        return type;
    }

    public boolean isStrike() {
        return STRIKE.equals(type);
    }

    public boolean isOrdinary() {
        return type.equals(ORDINARY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score1 = (Score) o;

        if (score != score1.score) return false;
        return type == score1.type;
    }

    @Override
    public int hashCode() {
        int result = score;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
