package bowling.domain.frame;

import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

public class NormalFrame implements Frame {

    private static final int ONE = 1;
    private static final int SEMI_FINAL_INDEX = 9;

    private final int round;
    private State state;

    private NormalFrame(int round, int pins) {
        this.round = round;
        this.state = Ready.of(pins);
    }

    public static NormalFrame bowling(int round, int pins) {
        return new NormalFrame(round, pins);
    }

    @Override
    public Frame bowling(int pins) {
        this.state = this.state.bowling(pins);
        return this;
    }

    @Override
    public Frame next(int pins) {
        int nextIndex = Math.addExact(this.round, ONE);
        if (this.round == SEMI_FINAL_INDEX) {
            return FinalFrame.lastBowling(pins);
        }

        return bowling(nextIndex, pins);
    }

    @Override
    public int round() {
        return this.round;
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    @Override
    public boolean isFinishBowling() {
        return this.state.is(Strike.class) || this.state.is(Spare.class);
    }

    @Override
    public String currentResult() {
        return this.state.symbol();
    }

    @Override
    public State state() {
        return this.state;
    }
}
