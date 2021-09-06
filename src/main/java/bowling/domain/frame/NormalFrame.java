package bowling.domain.frame;

import java.util.Objects;

public class NormalFrame {

    private final int number;
    private final Rollings rollings;

    public NormalFrame(Rollings rollings, int number) {
        this.rollings = rollings;
        this.number = number;
    }

    public NormalFrame(int first) {
        this(Rollings.first(first), 1);
    }

    public NormalFrame(int first, int second) {
        this(Rollings.first(first).second(second), 1);
    }

    public NormalFrame next(int first) {
        if (!rollings.allRolled()) {
            throw new CannotNextFrameException();
        }
        if (this.number == 9) {
            //TODO 마지막 프레임 리턴
        }
        return new NormalFrame(Rollings.first(first).second(0), this.number + 1);
    }

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
