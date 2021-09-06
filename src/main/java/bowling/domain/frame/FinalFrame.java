package bowling.domain.frame;

import java.util.Objects;

public class FinalFrame implements Frame {

    private final FinalRollings finalRollings;

    public FinalFrame(FinalRollings finalRollings) {
        this.finalRollings = finalRollings;
    }

    public FinalFrame(int first) {
        this(FinalRollings.first(first));
    }

    public FinalFrame roll(int fallenPin) {
        if (finalRollings == null) {
            return new FinalFrame(FinalRollings.first(fallenPin));
        }
        return new FinalFrame(finalRollings.roll(fallenPin));
    }

    @Override
    public boolean isEnd() {
        return finalRollings.allRolled();
    }

    @Override
    public Frame next() {
        throw new IllegalStateException();
    }

    @Override
    public int number() {
        return 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(finalRollings, that.finalRollings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(finalRollings);
    }
}
