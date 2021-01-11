package bowling.domain.state;

import bowling.domain.PitchResults;
import bowling.domain.Score;

public class Strike extends Finished {

    private static final String STRIKE = "X";

    public Strike(PitchResults pitchResults){
        this.pitchResults = pitchResults;
    }

    @Override
    public Score createScore(int previousScore) {

        int sumUp = previousScore + sumUpCurrentResult();

        return Score.ofStrike(sumUp);
    }

    @Override
    public int getPitchTryCount() {
        return MIN_PITCH_COUNT;
    }

    @Override
    public String toString() {
        return STRIKE;
    }
}
