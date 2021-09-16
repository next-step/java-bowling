package bowling.model;

import java.util.Objects;

public class Gutter implements State {
    private Point point;

    public Gutter() {
        this.point = new Point(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gutter gutter = (Gutter) o;
        return Objects.equals(point, gutter.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}
