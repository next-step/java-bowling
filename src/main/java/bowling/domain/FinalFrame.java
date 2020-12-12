package bowling.domain;

import bowling.InvalidFrameIndexException;

import static util.Preconditions.checkArgument;

public class FinalFrame extends Frame {
    public static final int FINAL_FRAME_INDEX = 9;

    private Pins bonusPitching;
    private State state = State.BEFORE_FIRST_PITCHING;

    private FinalFrame(final int index) {
        super(index);
    }
    
    public static FinalFrame of(final int index) {
        checkArgument(index == FINAL_FRAME_INDEX, new InvalidFrameIndexException());
        return new FinalFrame(index);
    }

    @Override
    public void pitch(final Pins pins) {
        if (state == State.BEFORE_FIRST_PITCHING) {
            this.firstPitching = pins;
        }
        if (state == State.BEFORE_SECOND_PITCHING) {
            this.secondPitching = pins;
        }
        if (isBonusPitchable()) {
            this.bonusPitching = pins;
        }
        this.state = State.nextState(firstPitching, secondPitching);
    }

    @Override
    public boolean isPitchable() {
        return state == State.BEFORE_FIRST_PITCHING
                || state == State.BEFORE_SECOND_PITCHING
                || isBonusPitchable();
    }

    private boolean isBonusPitchable() {
        return state == State.STRIKE || state == State.SPARE;
    }
}
