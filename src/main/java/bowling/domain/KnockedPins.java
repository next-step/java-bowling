package bowling.domain;

public class KnockedPins {
    private final int count;

    public KnockedPins(int count) {
        validationCheck(count);
        this.count = count;
    }

    public void validationCheck(int score) {
        if (score > 10 || score < 0) {
            throw new IllegalArgumentException("score must be between 0 and 10");
        }
    }

    public KnockedPins add(KnockedPins knockedPins) {
        return new KnockedPins(this.count + knockedPins.getCount());
    }

    public boolean isZero() {
        return count == 0;
    }

    public boolean isStrike() {
        return count == 10;
    }

    public String convert() {
        if (isZero()) {
            return "-";
        }
        if (isStrike()) {
            return "X";
        }
        return Integer.toString(count);
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnockedPins knockedPins1 = (KnockedPins) o;

        return count == knockedPins1.count;
    }

    @Override
    public int hashCode() {
        return count;
    }
}
