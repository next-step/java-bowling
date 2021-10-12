package bowling.domain.frame;

import bowling.domain.FrameResult;
import bowling.domain.PinCount;

public interface Frame {
    Frame play(PinCount pinCount);

    FrameNumber getFrameNumber();

    FrameResult makeResult();
}
