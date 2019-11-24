package bowling;

import java.util.Objects;

public class Frame {

    private int i;
    private int i1;

    public Frame(int i, int i1) {
        this.i = i;
        this.i1 = i1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return i == frame.i &&
                i1 == frame.i1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, i1);
    }
}
