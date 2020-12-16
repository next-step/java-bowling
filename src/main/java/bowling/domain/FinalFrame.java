package bowling.domain;

import bowling.InvalidFrameIndexException;

import static util.Preconditions.checkArgument;

public class FinalFrame extends Frame {
    public static final int FINAL_FRAME_INDEX = 9;
    private FinalFrameState state = FinalFrameState.FIRST_PITCHING;

    private FinalFrame(final int index) {
        super(index);
    }
    
    public static FinalFrame of(final int index) {
        checkArgument(index == FINAL_FRAME_INDEX, new InvalidFrameIndexException());
        return new FinalFrame(index);
    }

    @Override
    public void pitch(final Pins pins) {
        pitchingResults.add(pins);
        this.state = FinalFrameState.nextState(pitchingResults.get(0), pitchingResults.get(1), state);
    }

    @Override
    public boolean isPlayable() {
        return state.isPitchable();
    }

    @Override
    public boolean isFinishedAll() {
        return !isPlayable();
    }
}
