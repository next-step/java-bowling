package bowling.domain;

import bowling.InvalidFrameIndexException;

import static util.Preconditions.checkArgument;

public class NormalFrame extends Frame {
    public static final int NORMAL_FRAME_MAXIMUM_INDEX = 8;

    private NormalFrameState state = NormalFrameState.FIRST_PITCHING;

    private NormalFrame(final int index) {
        super(index);
    }

    public static NormalFrame of(final int index) {
        checkArgument(index <= NORMAL_FRAME_MAXIMUM_INDEX, new InvalidFrameIndexException());
        return new NormalFrame(index);
    }

    @Override
    public void pitch(final Pins pins) {
        pitchingResults.add(pins);
        this.state = NormalFrameState.nextState(pitchingResults.get(0), pitchingResults.get(1));
    }

    @Override
    public boolean isPlayable() {
        return state.isPitchable();
    }

    @Override
    public boolean isFinishedAll() {
        return false;
    }
}