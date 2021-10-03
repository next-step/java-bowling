package bowling.domain.frame;

import bowling.domain.state.State;
import bowling.domain.state.running.Ready;

public class NormalFrame extends AbstractFrame {

    private Frame nextFrame;

    private NormalFrame(int round, Frame nextFrame, State state) {
        super(round, state);
        this.nextFrame = nextFrame;
    }

    static Frame from(int round, Frame frame, State state) {
        return new NormalFrame(round, frame, state);
    }

    public static Frame createFirstFrame() {
        return new NormalFrame(FIRST_ROUND, null, new Ready());
    }

    @Override
    public Frame createNextFrame() {
        if (round() + 1 == FINAL_ROUND) {
            return nextFrame = FinalFrame.of(new Ready());
        }
        return nextFrame = NormalFrame.from(round() + 1, null, new Ready());
    }

    @Override
    public Frame nextFrame() {
        return nextFrame;
    }

}
