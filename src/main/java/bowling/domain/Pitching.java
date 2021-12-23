package bowling.domain;

import java.util.Objects;

public class Pitching {

    public static final int FIRST_PITCHING = 1;
    public static final int SECOND_PITCHING = 2;
    public static final int PLUS_NUMBER = 1;

    private final int count;

    public Pitching(int count) {
        this.count = count;
    }

    public Pitching plusCount() {
        return new Pitching(this.count + PLUS_NUMBER);
    }

    public boolean isFirstPitching() {
        return this.count == FIRST_PITCHING;
    }

    public boolean isSecondPitching() {
        return this.count == SECOND_PITCHING;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pitching pitching = (Pitching) o;
        return count == pitching.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return "Try{" +
                "count=" + count +
                '}';
    }

}
