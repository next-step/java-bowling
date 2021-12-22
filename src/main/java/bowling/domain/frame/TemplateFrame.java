package bowling.domain.frame;

import bowling.domain.state.Start;
import bowling.domain.state.State;
import bowling.domain.state.end.End;

public abstract class TemplateFrame implements Frame {
    protected final FrameInfo frameInfo;
    protected State state;

    protected TemplateFrame() {
        this(FrameInfo.init(), new Start());
    }

    protected TemplateFrame(FrameInfo frameInfo, State state) {
        this.frameInfo = frameInfo;
        this.state = state;
    }

    @Override
    public boolean isEnd() {
        return state instanceof End;
    }

    @Override
    public int no() {
        return frameInfo.no();
    }

    @Override
    public int currentFallDownPinsCount() {
        return frameInfo.currentFallDownPinsCount();
    }
}
