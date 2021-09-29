package bowling.domain.score;

import java.util.Objects;

public class NormalScore implements Score {

    private final Pin first;
    private final Pin second;

    private NormalScore(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    static Score of(Pin first, Pin second) {
        return new NormalScore(first, second);
    }

    public static Score emptyScore() {
        return new NormalScore(null, null);
    }

    @Override
    public Score saveNextPin(Pin pin) {
        if (Objects.isNull(first)) {
            return new NormalScore(pin, null);
        }
        return null;
    }

    @Override
    public boolean isNextStorable() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NormalScore that = (NormalScore) o;
        return Objects.equals(first, that.first) && Objects.equals(second, that.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

}
