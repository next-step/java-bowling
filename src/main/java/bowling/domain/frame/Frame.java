package bowling.domain.frame;

import bowling.domain.Content;
import bowling.domain.pin.Pins;
import bowling.exception.NotCreateFrameException;

public interface Frame {

    Frame next() throws NotCreateFrameException;
    void bowling(int hit);
    boolean isFinish();
    Pins pins();
    Content content();
}
