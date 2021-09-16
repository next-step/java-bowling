package bowling.model;

import java.util.Objects;

public class Strike implements State {
    private Point point;

    public Strike() {
        this.point = new Point(10);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Strike strike = (Strike) o;
        return Objects.equals(point, strike.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}
