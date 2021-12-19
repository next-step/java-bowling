package bowling.domain;

import bowling.domain.state.End;
import bowling.domain.state.Start;
import bowling.domain.state.State;
import bowling.strategy.PitchNumberStrategy;

import java.util.List;

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
    public void run(PitchNumberStrategy numberStrategy) {
        Pitch pitch = Pitch.init();
        while (progressing()) {
            pitch = pitch.next(numberStrategy);
            state.run(pitch, this);
        }
    }

    private boolean progressing() {
        return state.progressing();
    }

    @Override
    public boolean isEnd() {
        return state instanceof End;
    }

    @Override
    public List<String> pitchResults() {
        return frameInfo.pitchResults();
    }

    @Override
    public int no() {
        return frameInfo.no();
    }
}
