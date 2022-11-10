package bowling.domain.frame;

import java.util.List;

public class Score {
    private final List<Integer> falledPinsList;
    private final boolean isSpare;

    public Score(List<Integer> falledPinsList) {
        this(falledPinsList, false);
    }

    public Score(List<Integer> falledPinsList, boolean isSpare) {
        this.falledPinsList = falledPinsList;
        this.isSpare = isSpare;
    }

    public boolean isStrike() {
        return falledPinsList.size() == 1 && falledPinsList.get(0) == 10;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public List<Integer> getValues() {
        return falledPinsList;
    }
}
