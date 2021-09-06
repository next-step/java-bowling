package bowling.domain.frame;

import java.util.Objects;

public class Rollings {

    private final Rolling first;
    private final Rolling second;

    private Rollings(Rolling first, Rolling second) {
        validateRollings(first, second);
        this.first = first;
        this.second = second;
    }

    private void validateRollings(Rolling first, Rolling second) {
        RollingsValidation rollingsValidation = RollingsValidation.of(first, second);
        if (rollingsValidation != RollingsValidation.NONE) {
            throw new RollingsException(rollingsValidation.message());
        }
    }
    public static Rollings first(int first) {
        return new Rollings(new Rolling(first), null);
    }

    public Rollings second(int second) {
        return new Rollings(this.first, new Rolling(second));
    }

    public boolean isStrike() {
        return this.first.isStrike();
    }

    public boolean isSpare() {
        if (second == null) {
            return false;
        }
        return !isStrike() && second.isSpare(first);
    }

    public int sum() {
        return first.sum(second);
    }

    public boolean allRolled() {
        if (isStrike() || isSpare()) {
            return true;
        }
        return !isStrike() && second != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rollings rollings = (Rollings) o;
        return Objects.equals(first, rollings.first) && Objects.equals(second, rollings.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
