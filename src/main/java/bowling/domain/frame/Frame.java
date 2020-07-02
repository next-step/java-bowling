package bowling.domain.frame;

import bowling.domain.pin.Pins;

public interface Frame {
    void roll(int pin);

    int getPins();

    boolean canRoll();

    int getIndex();
}
