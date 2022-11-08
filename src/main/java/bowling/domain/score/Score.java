package bowling.domain.score;

public class Score {
    private final int score;
    private final int leftTries;

    public Score(int score, int leftTries) {
        this.score = score;
        this.leftTries = leftTries;
    }

    public boolean canCalculate() {
        return leftTries == 0;
    }

    public Score bowl(int score) {
        return new Score(this.score + score, leftTries - 1);
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;

        Score score1 = (Score) o;

        if (score != score1.score) return false;
        return leftTries == score1.leftTries;
    }

    @Override
    public int hashCode() {
        int result = score;
        result = 31 * result + leftTries;
        return result;
    }
}
