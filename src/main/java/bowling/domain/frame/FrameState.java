package bowling.domain.frame;

import bowling.domain.FrameEnum;
import bowling.domain.Rolls;

interface FrameState {
    FrameEnum getFrameEnum();

    int getScore(FrameContext context, Rolls rolls);

    boolean hasScore(FrameContext context, Rolls rolls);

    void update(FrameContext context, Rolls rolls);
}
