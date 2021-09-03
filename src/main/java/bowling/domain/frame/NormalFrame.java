package bowling.domain.frame;

import java.util.Objects;

public class NormalFrame {

    private final Pitching pitching;

    public NormalFrame(Pitching pitching) {
        this.pitching = pitching;
    }

    public NormalFrame(int first) {
        this(Pitching.first(first));
    }

    public NormalFrame(int first, int second) {
        this(Pitching.first(first).second(second));
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
