package bowling.domain;

import java.util.Objects;

public class Bowling {

    public static final int MIN = 0;
    public static final int MAX = 10;
    private final int count;

    private Bowling(int count) {
        if (isNotValidCount(count)) {
            throw new IllegalArgumentException("0 ~ 10 사이의 정수만 가능합니다");
        }

        this.count = count;
    }

    private boolean isNotValidCount(int count) {
        return count < MIN || count > MAX;
    }

    public static Bowling of(int count) {
        return new Bowling(count);
    }

    public int getCount() {
        return count;
    }

    //==============================================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bowling bowling = (Bowling) o;
        return count == bowling.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return count + "";
    }
}
