package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.domain.state.State;

import static java.lang.Math.addExact;

public final class NormalFrame implements Frame {

    private static final int INCREASE_UNIT = 1;

    private State state;
    private final int sequence;

    public NormalFrame(final int sequence) {
        this.state = State.initialize();
        this.sequence = sequence;
    }

    @Override
    public Frame bowl(final int pins) {
        return bowl(Pins.valueOf(pins));
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
            return new FinalFrame();
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

    @Override
    public final Score calculateAdditionalScore(final Score beforeScore) {
        if (beforeScore.isFinish()) {
            return beforeScore;
        }
        return state.calculateAdditionalScore(beforeScore);
    }

    @Override
    public final String description() {
        return state.description();
    }
}
