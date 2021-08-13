package bowling.domain.state;

import bowling.domain.pin.Pins;

import java.util.Collections;
import java.util.List;

public class Start extends CommonState {

    private Start() {
    }

    public static Start of() {
        return new Start();
    }

    @Override
    public CommonState hitPins(Pins pins) {
        if (pins.isAllHit()) {
            return Strike.of();
        }

        return Progress.of(pins);
    }

    @Override
    public List<Integer> getHitPins() {
        return Collections.emptyList();
    }

}
