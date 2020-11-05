package bowling.frame;

import bowling.frame.state.Set;
import bowling.frame.state.State;

import java.util.List;

public abstract class Frame {

    public static final int FIRST_FRAME_NUMBER = 1;
    public static final int FINAL_FRAME_NUMBER = 10;
    public static final int INCREASE_FRAME_NUMBER = 1;

    protected int frameNumber;
    protected State state;

    public Frame(int frameNumber) {
        this.frameNumber = frameNumber;
        this.state = Set.init();
    }

    protected abstract Frame bowl(String fellPins);

    public abstract boolean isFinish();

    public int getFrameNumber() {
        return frameNumber;
    }

    public abstract List<String> getBowlResults();

    public abstract State getState();
}
