package bowling.domain;

import java.util.Objects;

public class Position {

    private final int position;

    private static final int FIRST_POSITION = 0;
    private static final int FINAL_POSITION = 9;

    private Position(int position) {
        this.position = position;
    }

    public static Position first() {
        return new Position(FIRST_POSITION);
    }

    public static Position of(int position) {
        return new Position(position);
    }

    public Position next() {
        return new Position(this.position + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position1 = (Position) o;
        return position == position1.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    public boolean isFinal() {
        return position == FINAL_POSITION;
    }
}
