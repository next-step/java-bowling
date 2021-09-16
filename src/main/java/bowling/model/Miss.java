package bowling.model;

import java.util.Objects;

public class Miss implements GameResult {
    private Point point;

    public Miss() {
        this.point = new Point(0);
    }

    public Miss(Point point) {
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miss miss = (Miss) o;
        return Objects.equals(point, miss.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}
