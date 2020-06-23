package bowling.domain.state.running;

import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;
import bowling.domain.state.State;
import bowling.domain.state.StateExpression;
import bowling.domain.state.finish.Strike;

public class Ready extends Running {

    private Ready() {
    }

    public static Ready newInstance() {
        return new Ready();
    }

    @Override
    public State bowl(final PinCount hitCount) {
        Pins pins = Pins.of(hitCount);

        if (pins.isStrike()) {
            return Strike.newInstance();
        }
        return Hit.of(pins);
    }

    @Override
    public String getDesc() {
        return StateExpression.READY;
    }
}
