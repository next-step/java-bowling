package bowling.domain;

public class KnockedPins {
    private final int knockedPins;

    public KnockedPins(int knockedPins) {
        validationCheck(knockedPins);
        this.knockedPins = knockedPins;
    }

    public void validationCheck(int score) {
        if (score > 10 || score < 0) {
            throw new IllegalArgumentException("score must be between 0 and 10");
        }
    }

    public KnockedPins add(KnockedPins knockedPins) {
        return new KnockedPins(this.knockedPins + knockedPins.getKnockedPins());
    }

    public boolean isZero() {
        return knockedPins == 0;
    }

    public boolean isStrike() {
        return knockedPins == 10;
    }

    public String convert() {
        if (isZero()) {
            return "-";
        }
        if (isStrike()) {
            return "X";
        }
        return Integer.toString(knockedPins);
    }

    public int getKnockedPins() {
        return knockedPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnockedPins knockedPins1 = (KnockedPins) o;

        return knockedPins == knockedPins1.knockedPins;
    }

    @Override
    public int hashCode() {
        return knockedPins;
    }
}
