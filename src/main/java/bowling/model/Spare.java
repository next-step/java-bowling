package bowling.model;

import java.util.Objects;

public class Spare implements GameResult {
    private Point point;

    public Spare() {
        this.point = new Point(10);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return Objects.equals(point, spare.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}
