package bowling.domain.frame;

import bowling.domain.Pins;

public interface Frame {
    Frame bowl(Pins hitPins);

    boolean isFrameEnd();

    boolean isFinalFrame();

    String symbol();
}