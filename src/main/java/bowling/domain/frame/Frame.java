package bowling.domain.frame;

import bowling.domain.pin.Pins;

public interface Frame {
    boolean isFinished();

    Frame bowl(Pins pins);

    String print();
}
