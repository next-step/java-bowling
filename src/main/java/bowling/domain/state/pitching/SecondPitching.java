package bowling.domain.state.pitching;

import bowling.domain.pin.Pins;
import bowling.domain.state.CommonState;
import bowling.domain.state.result.Miss;
import bowling.domain.state.result.Spare;

import java.util.Collections;
import java.util.List;

public class SecondPitching implements CommonState {
    private final Pins pins;

    private SecondPitching(Pins pins) {
        this.pins = pins;
    }

    public static SecondPitching of(Pins pins) {
        return new SecondPitching(pins);
    }

    @Override
    public CommonState hitPins(Pins pins) {
        Pins hitPinsTotal = this.pins.add(pins);

        if (hitPinsTotal.isAllHit()) {
            return Spare.of(this.pins);
        }

        return Miss.of(this.pins, pins);
    }

    @Override
    public List<Integer> getHitPins() {
        return Collections.singletonList(pins.getCountHitPins());
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    @Override
    public boolean isAllHit() {
        return false;
    }
}
