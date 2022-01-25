package bowling.domain;

public class KnockedPins {
    private final int score;

    public KnockedPins(int score) {
        validationCheck(score);
        this.score = score;
    }

    public void validationCheck(int score) {
        if (score > 10 || score < 0) {
            throw new IllegalArgumentException("score must be between 0 and 10");
        }
    }

    public KnockedPins add(KnockedPins knockedPins) {
        return new KnockedPins(this.score + knockedPins.getScore());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnockedPins knockedPins1 = (KnockedPins) o;

        return score == knockedPins1.score;
    }

    @Override
    public int hashCode() {
        return score;
    }
}
