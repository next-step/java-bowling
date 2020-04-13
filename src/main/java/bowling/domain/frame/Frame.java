package bowling.domain.frame;

import bowling.domain.pin.Pins;

import java.util.Optional;

public interface Frame {
    Optional<Frame> getNext();

    void bowl(Pins pins);
}
