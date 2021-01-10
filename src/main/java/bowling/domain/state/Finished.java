package bowling.domain.state;

import bowling.domain.PitchResults;

public abstract class Finished implements State {

    protected static final int MIN_PITCH_COUNT = 1;
    protected static final int MAX_PITCH_COUNT = 2;
    protected static final String DELIMITER = "|";

    protected PitchResults pitchResults;

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public State pitch(int knockedDownPins) {
        return null;
    }

    @Override
    public int sumUpCurrentResult(){
        return pitchResults.sumUpCurrentResult();
    }

    @Override
    public PitchResults getPitchResults() {
        return pitchResults;
    }
}
