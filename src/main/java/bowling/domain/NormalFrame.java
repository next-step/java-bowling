package bowling.domain;

import bowling.InvalidFrameIndexException;

import static util.Preconditions.checkArgument;

public class NormalFrame extends Frame {
    public static final int NORMAL_FRAME_MAXIMUM_INDEX = 8;
    
    private State state = State.BEFORE_FIRST_PITCHING;
    
    private NormalFrame(final int index) {
        super(index);
    }
    
    public static NormalFrame of(final int index) {
        checkArgument(index <= NORMAL_FRAME_MAXIMUM_INDEX, new InvalidFrameIndexException());
        return new NormalFrame(index);
    }

    @Override
    public void pitch(final Pins pins) {
        if (state == State.BEFORE_FIRST_PITCHING) {
            this.firstPitching = pins;
        }
        if (state == State.BEFORE_SECOND_PITCHING) {
            this.secondPitching = pins;
        }
        this.state = State.nextState(firstPitching, secondPitching);
    }

    @Override
    public boolean isPitchable() {
        return state.isPitchable();
    }
}