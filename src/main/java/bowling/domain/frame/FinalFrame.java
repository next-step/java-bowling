package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.frame.presenter.FinalFramePresenter;

public class FinalFrame extends Frame {
    public static final int INDEX = 9;
    
    private FinalFrameState state = FinalFrameState.FIRST_PITCHING;

    private FinalFrame(final int index) {
        super(index);
    }
    
    public static FinalFrame of() {
        return new FinalFrame(INDEX);
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

    @Override
    public String getSymbol() {
        return FinalFramePresenter.present(pitchingResults.getAllFallenPin());
    }
}
