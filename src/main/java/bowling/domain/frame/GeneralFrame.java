package bowling.domain.frame;

import bowling.domain.state.Preparation;
import bowling.domain.state.State;

import java.util.List;

public class GeneralFrame extends Frame {
    private static final int LIMIT_SIZE_OF_GENERAL_FRAME = 9;

    private Frame nextFrame;

    private GeneralFrame(State state) {
        super(state);
        nextFrame = new DummyFrame(Preparation.init());
    }

    public static GeneralFrame init() {
        return new GeneralFrame(Preparation.init());
    }

    @Override
    protected void appendFrame(List<Frame> frames) {
        if (isEnd()) {
            initNextFrame(frames);
        }
    }

    private void initNextFrame(List<Frame> frames) {
        if (frames.size() < LIMIT_SIZE_OF_GENERAL_FRAME) {
            updateNextFrame(frames, GeneralFrame.init());
            return;
        }

        updateNextFrame(frames, LastFrame.init());
    }

    private void updateNextFrame(List<Frame> frames, Frame nextFrame) {
        this.nextFrame = nextFrame;
        frames.add(nextFrame);
    }
}
