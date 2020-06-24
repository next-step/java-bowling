package bowling.domain.state.finish;

import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;

public class Strike extends Finished {

    private Strike() {
    }

    private static class InnerInstanceClass {
        private static final Strike instance = new Strike();
    }

    public static Strike getInstance() {
        return InnerInstanceClass.instance;
    }

    @Override
    public Pins getFirstPins() {
        return Pins.of(PinCount.MAX_COUNT);
    }
}
