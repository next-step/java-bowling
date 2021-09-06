package bowling.step2.domain.visitor;

import bowling.step2.domain.Frame;
import bowling.step2.domain.LastFrame;
import bowling.step2.domain.NormalFrame;

public interface FrameVisitor {
    Frame nextFrame(NormalFrame normalFrame);

    Frame nextFrame(LastFrame normalFrame);
}
