package bowling.domain.score;

import java.util.List;
import java.util.Objects;

public abstract class Score {

    final Pin first;
    final Pin second;

    Score(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    public static boolean isSpare(Pin first, Pin second) {
        if (first == Pin.of(10)) {
            return false;
        }
        return first.sum(second) == Pin.of(10);
    }

    public abstract Score nextPin(Pin pin);

    public abstract boolean isNext();

    public abstract List<Pin> values();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        return Objects.equals(first, score.first) && Objects.equals(second, score.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

}
