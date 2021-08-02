package bowling.domain.state;

import bowling.domain.pin.DownedPins;

import java.util.Collections;
import java.util.List;

public class Spare extends EndState {
    private final DownedPins downedPins;

    private Spare(DownedPins downedPins) {
        this.downedPins = downedPins;
    }

    public static Spare from(DownedPins downedPins) {
        return new Spare(downedPins);
    }

    @Override
    protected boolean isClean() {
        return true;
    }

    @Override
    public List<Integer> getDownedPins() {
        return Collections.singletonList(downedPins.getNumOfDownedPins());
    }
}
