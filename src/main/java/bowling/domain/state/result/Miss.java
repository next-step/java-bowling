package bowling.domain.state.result;

import bowling.domain.pin.Pins;

import java.util.Arrays;
import java.util.List;

public class Miss extends End {
    private final Pins firstPins;
    private final Pins secondPins;

    private Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static Miss of(Pins firstPins, Pins secondPins) {
        return new Miss(firstPins, secondPins);
    }

    @Override
    public boolean isMiss() {
        return true;
    }

    @Override
    public List<Integer> getHitPins() {
        return Arrays.asList(firstPins.getCountHitPins(), secondPins.getCountHitPins());
    }

}
