package bowling_step3.domain.state;

import bowling_step3.domain.Pitch;
import bowling_step3.exception.PitchOverBoundException;

public abstract class Finished implements State {

    protected static final int MIN_PITCH_COUNT = 1;

    protected static final int MAX_PITCH_COUNT = 2;

    protected static final int BOWLING_MAX_TOTAL = 10;

    protected Pitch firstPitch;

    protected Pitch secondPitch;

    @Override
    public State pitch(int fallenPins) {
        throw new PitchOverBoundException();
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
