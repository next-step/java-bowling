package bowling.application;

import bowling.domain.frame.Frames;
import bowling.domain.pin.Pins;

public class BowlingService {
    private Frames frames;

    public BowlingService() {
        frames = Frames.init();
    }

    public Frames play(Pins pins) {
        return frames.bowl(pins);
    }
}
