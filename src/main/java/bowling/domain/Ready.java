package bowling.domain;

import static bowling.domain.PinCount.TEN;

public class Ready implements FrameState {
    @Override
    public FrameState bowl(PinCount pinCount) {
        if (TEN.equals(pinCount)) {
            return Finished.strike();
        }
        return new Proceeding(pinCount);
    }
}
