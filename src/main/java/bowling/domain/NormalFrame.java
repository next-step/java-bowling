package bowling.domain;

import java.util.Objects;

public class NormalFrame extends Frame {

    public NormalFrame(int number) {
        super(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return number == that.number && Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, results);
    }

}
