package bowling.domain.frame;

import java.util.Objects;

public class NormalFrame implements Frame {

    public static final int INIT_NUMBER = 1;
    public static final int LAST_NORMAL_NUMBER = 9;


    private final int number;
    private final Rollings rollings;

    public NormalFrame(Rollings rollings, int number) {
        this.rollings = rollings;
        this.number = number;
    }

    public NormalFrame(Rollings rollings) {
        this.rollings = rollings;
        this.number = INIT_NUMBER;
    }

    public NormalFrame(int first) {
        this(Rollings.first(first), INIT_NUMBER);
    }

    public NormalFrame roll(int fallenPin) {
        if (this.rollings == null) {
            return new NormalFrame(Rollings.first(fallenPin), this.number);
        }
        return new NormalFrame(this.rollings.second(fallenPin), this.number);
    }

    @Override
    public Frame next() {
        if (!rollings.allRolled()) {
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
        return rollings.allRolled();
    }

    @Override
    public int number() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(rollings, that.rollings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollings);
    }
}
