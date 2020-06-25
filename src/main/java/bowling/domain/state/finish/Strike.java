package bowling.domain.state.finish;

import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;

public class Strike extends Finished {

    private static final Strike INSTANCE = new Strike();

    private Strike() {
    }

    public static Strike getInstance() {
        return INSTANCE;
    }

    @Override
    public Pins getFirstPins() {
        return Pins.of(PinCount.MAX_COUNT);
    }
}
