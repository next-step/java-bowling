package bowling.step2.domain.visitor;

import bowling.step2.domain.Frame;
import bowling.step2.domain.LastFrame;
import bowling.step2.domain.NormalFrame;

public class FrameVisitorImpl implements FrameVisitor{
    @Override
    public Frame nextFrame(NormalFrame normalFrame) {
        if (normalFrame.isLastOfNormalFrame()) {
            return LastFrame.of(normalFrame.frameNo() + 1);
        }

        return NormalFrame.of(normalFrame.frameNo() + 1);
    }

    @Override
    public Frame nextFrame(LastFrame normalFrame) {
        return null;
    }
}
