package bowling;

import java.util.List;
import java.util.Objects;

public class NormalFrame {

    private int index;

    private List<Point> points;

    private static final int START_INDEX = 0;
    private static final int NEXT_INDEX_DISTANCE = 1;


    private NormalFrame() {
        this.index = START_INDEX;
    }

    public NormalFrame(int index) {
        this.index = index;
    }

    public static NormalFrame first() {
        return new NormalFrame();
    }

    public NormalFrame next() {
        return new NormalFrame(this.index + NEXT_INDEX_DISTANCE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return index == that.index && Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, points);
    }
}
