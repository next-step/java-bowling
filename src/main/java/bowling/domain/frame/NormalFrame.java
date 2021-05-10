package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.domain.state.State;
import bowling.domain.state.running.Ready;

import static java.lang.Math.addExact;

public final class NormalFrame extends Frame {

    private static final int INCREASE_UNIT = 1;

    private State state;
    private final int sequence;

    private NormalFrame(final int sequence) {
        this.state = Ready.initialize();
        this.sequence = sequence;
    }

    public static final Frame from(final int sequence) {
        return new NormalFrame(sequence);
    }

    @Override
    public final Frame bowl(final Pins fallPins) {
        state = state.bowl(fallPins);
        if (state.isFinish()) {
            return generateNextFrame();
        }
        return this;
    }

    private final Frame generateNextFrame() {
        final int nextSequence = addExact(sequence, INCREASE_UNIT);
        if (nextSequence == LAST_SEQUENCE) {
            return FinalFrame.initialize();
        }
        return new NormalFrame(nextSequence);
    }

    @Override
    public final boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public final Score score() {
        return state.score();
    }

    @Override
    public final int sequence() {
        return sequence;
    }
}
