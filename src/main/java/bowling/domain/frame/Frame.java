package bowling.domain.frame;

import bowling.domain.pin.Pin;

public interface Frame {

    boolean pitch(Pin pin);

    Frame next();

    boolean hasNextFrame();

    int getNumber();
}
