package bowling.domain.state;

import bowling.domain.pin.DownedPins;
import bowling.domain.score.Score;

import java.util.Arrays;
import java.util.List;

public class Miss extends EndState {
    private final DownedPins firstDownedPins;
    private final DownedPins secondDownedPins;

    private Miss(DownedPins firstDownedPins, DownedPins secondDownedPins) {
        this.firstDownedPins = firstDownedPins;
        this.secondDownedPins = secondDownedPins;
    }

    public static Miss of(DownedPins firstDownedPins, DownedPins secondDownedPins) {
        return new Miss(firstDownedPins, secondDownedPins);
    }

    @Override
    public Score Score() {
        return null;
    }

    @Override
    protected boolean isMiss() {
        return true;
    }

    @Override
    public List<Integer> getDownedPins() {
        return Arrays.asList(firstDownedPins.getNumOfDownedPins(), secondDownedPins.getNumOfDownedPins());
    }
}
