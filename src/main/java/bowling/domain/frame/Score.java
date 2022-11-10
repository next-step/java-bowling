package bowling.domain.frame;

import java.util.List;

public class Score {
    private final List<Integer> falledPins;
    private final boolean isSpare;

    public Score(List<Integer> falledPins) {
        this(falledPins, false);
    }

    public Score(List<Integer> falledPins, boolean isSpare) {
        this.falledPins = falledPins;
        this.isSpare = isSpare;
    }

    public boolean isStrike() {
        return falledPins.size() == 1 && falledPins.get(0) == 10;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public List<Integer> getValues() {
        return falledPins;
    }
}
