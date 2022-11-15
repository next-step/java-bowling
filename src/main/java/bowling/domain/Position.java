package bowling.domain;

import java.util.Objects;

public class Position {

    private final int position;


    public Position(int position) {
        this.position = position;
    }

    public Position next() {
        return new Position(position + 1);
    }

    public Position next(int bound) {
        if (position >= bound - 1) {
            return new Position(0);
        }
        return next();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position1 = (Position) o;
        return position == position1.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

}
