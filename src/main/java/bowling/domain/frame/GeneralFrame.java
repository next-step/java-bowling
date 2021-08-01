package bowling.domain.frame;

import bowling.domain.state.Preparation;
import bowling.domain.state.State;

import java.util.List;

public class GeneralFrame extends Frame {
    private static final int LIMIT_SIZE_OF_GENERAL_FRAME = 9;

    private GeneralFrame(State state) {
        super(state);
    }

    public static GeneralFrame init() {
        return new GeneralFrame(Preparation.instance());
    }

    @Override
    protected void appendFrame(List<Frame> frames) {
        if (isEnd()) {
            initNextFrame(frames);
        }
    }

    private void initNextFrame(List<Frame> frames) {
        if (frames.size() < LIMIT_SIZE_OF_GENERAL_FRAME) {
            frames.add(GeneralFrame.init());
            return;
        }

        frames.add(LastFrame.init());
    }
}
