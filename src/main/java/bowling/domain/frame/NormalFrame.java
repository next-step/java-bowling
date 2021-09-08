package bowling.domain.frame;

import bowling.domain.frame.rolling.NormalRollings;
import bowling.domain.frame.rolling.Rollings;

import java.util.Objects;

public class NormalFrame implements Frame {

    private final int number;
    private final NormalRollings normalRollings;

    public NormalFrame(NormalRollings normalRollings, int number) {
        this.normalRollings = normalRollings;
        this.number = number;
    }

    public NormalFrame(NormalRollings normalRollings) {
        this.normalRollings = normalRollings;
        this.number = INIT_NUMBER;
    }

    public NormalFrame(int first) {
        this(NormalRollings.first(first), INIT_NUMBER);
    }

    public NormalFrame roll(int fallenPin) {
        if (this.normalRollings == null) {
            return new NormalFrame(NormalRollings.first(fallenPin), this.number);
        }
        return new NormalFrame(this.normalRollings.second(fallenPin), this.number);
    }

    @Override
    public Frame next() {
        if (!normalRollings.allRolled()) {
            throw new CannotNextFrameException();
        }
        if (isLastNormalFrame()) {
            return new FinalFrame(null);
        }
        return new NormalFrame(null, this.number + 1);
    }

    private boolean isLastNormalFrame() {
        return this.number == LAST_NORMAL_NUMBER;
    }

    @Override
    public boolean isEnd() {
        return normalRollings.allRolled();
    }

    @Override
    public int number() {
        return number;
    }

    @Override
    public Rollings rollings() {
        return normalRollings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(normalRollings, that.normalRollings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(normalRollings);
    }
}
