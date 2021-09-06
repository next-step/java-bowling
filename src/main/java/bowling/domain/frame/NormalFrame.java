package bowling.domain.frame;

import java.util.Objects;

public class NormalFrame {

    private final int number;
    private final Pitching pitching;

    public NormalFrame(Pitching pitching, int number) {
        this.pitching = pitching;
        this.number = number;
    }

    public NormalFrame(int first) {
        this(Pitching.first(first), 1);
    }

    public NormalFrame(int first, int second) {
        this(Pitching.first(first).second(second), 1);
    }

    public NormalFrame next(int first) {
        if (!pitching.allPitched()) {
            throw new CannotNextFrameException();
        }
        if (this.number == 9) {
            //TODO 마지막 프레임 리턴
        }
        return new NormalFrame(Pitching.first(first).second(0), this.number + 1);
    }

    public int number() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(pitching, that.pitching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitching);
    }
}
