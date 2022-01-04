package bowling.domain.state.running;

import bowling.domain.Pins;
import bowling.domain.state.ThrowingState;
import bowling.domain.state.end.Strike;

public class Ready extends RunningState {

    private Ready() {}

    public static ThrowingState create() {
        return new Ready();
    }

    @Override
    public ThrowingState bowl(Pins pins) {
        if (pins.isStrike()) {
            return new Strike();
        }
        return FirstBowl.create(pins);
    }

    @Override
    public String symbol() {
        return "";
    }

    @Override
    public boolean isMiss() {
        return false;
    }
}
