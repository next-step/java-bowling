package bowling.frame;

import bowling.Pins;

public interface Frame {

    Frame bowl(Pins fallenPins);
    String symbol();
    int getFrameNo();
}
