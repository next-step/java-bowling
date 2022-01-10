package bowling.domain;

public class Score {
    private final int score;

    public Score(int score) {
        validationCheck(score);
        this.score = score;
    }

    public Score add(Score score) {
        return new Score(this.score + score.getScore());
    }

    public boolean isZero() {
        return score == 0;
    }

    public boolean isStrike() {
        return score == 10;
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

    public int getScore() {
        return score;
    }


    public void validationCheck(int score) {
        if (score > 10 || score < 0) {
            throw new IllegalArgumentException("score must be between 0 and 10");
        }
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
