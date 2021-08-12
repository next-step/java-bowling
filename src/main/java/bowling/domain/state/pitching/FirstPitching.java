package bowling.domain.state.pitching;

import bowling.domain.pin.Pins;
import bowling.domain.state.CommonState;
import bowling.domain.state.result.Strike;

import java.util.Collections;
import java.util.List;

public class FirstPitching extends CommonState {

    private FirstPitching() {
    }

    public static FirstPitching of() {
        return new FirstPitching();
    }

    @Override
    public CommonState hitPins(Pins pins) {
        if (pins.isAllHit()) {
            return Strike.of();
        }

        return SecondPitching.of(pins);
    }

    @Override
    public List<Integer> getHitPins() {
        return Collections.emptyList();
    }

}
