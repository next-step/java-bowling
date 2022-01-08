package bowling.domain;

public class Score {
    private int score;

    public Score(int score) {
        this.score = score;
    }

    public boolean isStrike() {
        if (score == 10) {
            return true;
        }
        return false;
    }

    public Score add(Score score) {
        return new Score(this.score + score.getScore());
    }

    public int getScore() {
        return score;
    }

    public boolean isZero() {
        if (score == 0) {
            return true;
        }
        return false;
    }

    public String convert() {
        if (isZero()) {
            return "-";
        }
        if (isStrike()) {
            return "X";
        }
        return Integer.toString(score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score1 = (Score) o;

        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return score;
    }
}
