package bowling.domain.state;

import bowling.domain.PitchResults;
import bowling.domain.Score;

public abstract class Running implements State{

    protected static final int MIN_PITCH_COUNT = 0;
    protected static final int MAX_PITCH_COUNT = 1;

    protected PitchResults pitchResults;

    @Override
    public boolean isFinish() {
        return false;
    }

    protected void addPitchResult(int knockedDownPins) {
        pitchResults.addNewResult(knockedDownPins);
    }

    @Override
    public int sumUpCurrentResult() {
        return pitchResults.sumUpCurrentResult();
    }

    @Override
    public Score createScore(int previousScore) {
        return Score.of(previousScore);
    }

    @Override
    public PitchResults getPitchResults() {
        return pitchResults;
    }
}
