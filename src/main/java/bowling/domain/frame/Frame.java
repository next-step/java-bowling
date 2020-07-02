package bowling.domain.frame;

import bowling.domain.status.Status;

public interface Frame {
    Status bowl(int downPin);

    Frame nextFrame(int index);

    String printFrameResult();

    boolean canPlayMore();
}
