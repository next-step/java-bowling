package bowling.domain;

import bowling.domain.state.State;

import static bowling.util.BowlingFixture.FRAME_LAST_INDEX;

public class FinalFrame implements Frame {

    private State state;
    private final int index;

    public static final Frame newInstance() {
        return new FinalFrame();
    }

    public FinalFrame() {
        this.state = State.initialize();
        this.index = FRAME_LAST_INDEX;
    }

    @Override
    public final Frame bowl(HitCount hitCOunt) {
        return null;
    }

    @Override
    public final boolean isFinish() {
        return false;
    }

    @Override
    public final int index() {
        return index;
    }

}
