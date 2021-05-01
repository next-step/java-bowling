package bowling.domain;

import bowling.domain.state.State;

import static bowling.util.BowlingFixture.*;

public class NormalFrame implements Frame {


    private State state;
    private Frame next;
    private final int index;

    public NormalFrame(final int index) {
        this.state = State.initialize();
        this.next = Frame.initialize();
        this.index = index;
    }

    public static final Frame initialize() {
        return new NormalFrame(FRAME_START_INDEX);
    }

    public static final Frame from(final int index) {
        return new NormalFrame(index);
    }

    @Override
    public final Frame bowl(final HitCount hitCOunt) {
        state = state.bowl(hitCOunt);
        if (state.isFinish()) {
            next = nextFrame();
            return next;
        }
        return this;
    }

    private final Frame nextFrame() {
        if (index + INCREASE_INDEX_UNIT == FRAME_LAST_INDEX) {
        }
        return new NormalFrame(index + INCREASE_INDEX_UNIT);
    }

}
