package bowling.domain.frame;

import java.util.Objects;

public abstract class Rollings {

    private final Rolling first;
    private final Rolling second;

    protected Rollings(Rolling first, Rolling second) {
        validateRollings(first, second);
        this.first = first;
        this.second = second;
    }

    protected abstract void validateRollings(Rolling first, Rolling second);

    public Rolling first() {
        return first;
    }

    public Rolling second() {
        return second;
    }

    public boolean isStrike() {
        return first.isStrike();
    }

    public boolean isSpare() {
        if (second == null) {
            return false;
        }
        return !isStrike() && second.isSpare(first);
    }

    public int sum() {
        return first.plusFallenPin(second);
    }

    public abstract boolean allRolled();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rollings that = (Rollings) o;
        return Objects.equals(first, that.first) && Objects.equals(second, that.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
