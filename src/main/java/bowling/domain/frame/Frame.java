package bowling.domain.frame;

import bowling.domain.pin.Pins;

import java.util.List;

public interface Frame extends FrameResult {

    boolean isBowlingFinish();

    void hitPins(Pins pins);

    default void addFrame(List<Frame> frames) {
    }

}
