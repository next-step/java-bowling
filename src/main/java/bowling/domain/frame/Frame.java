package bowling.domain.frame;

import bowling.domain.pin.Pins;

import java.util.List;

public interface Frame {
    boolean isBowlingFinish();
    void hitPins(Pins pins);
    void addFrame(List<Frame> frames);
}
