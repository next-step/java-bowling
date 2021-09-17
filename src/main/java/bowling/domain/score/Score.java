package bowling.domain.score;

import java.util.List;

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

}
