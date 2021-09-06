package bowling.domain.frame;

import java.util.Objects;

public class FinalRollings extends Rollings {

    private final Rolling third;

    public FinalRollings(Rolling first, Rolling second, Rolling third) {
        super(first, second);
        this.third = third;
    }

    @Override
    protected void validateRollings(Rolling first, Rolling second) {
        // nothing
    }

    public static FinalRollings first(int first) {
        return new FinalRollings(new Rolling(first), null, null);
    }

    public FinalRollings roll(int fallenPin) {
        if (allRolled()) {
            throw new FinalRollingsException();
        }
        if (second() == null) {
            return new FinalRollings(first(), new Rolling(fallenPin), null);
        }
        return new FinalRollings(first(), second(), new Rolling(fallenPin));
    }

    @Override
    public Rolling third() {
        return third;
    }

    @Override
    public boolean allRolled() {
        if (!isStrike() && second() == null) {
            return false;
        }
        if ((isStrike() || isSpare()) && third != null) {
            return true;
        }
        return !isStrike() && !isSpare();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FinalRollings that = (FinalRollings) o;
        return Objects.equals(third, that.third);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), third);
    }

}
