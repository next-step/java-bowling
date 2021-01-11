package bowling.domain.state;

import bowling.domain.PitchResults;
import bowling.domain.Score;

public class Spare extends Finished {

    private static final String SPARE = "/";

    public Spare(PitchResults pitchResults){
        this.pitchResults = pitchResults;
    }

    @Override
    public Score createScore(int previousScore) {

        int sumUp = previousScore + sumUpCurrentResult();

        return Score.ofSpare(sumUp);
    }

    @Override
    public int getPitchTryCount() {
        return MAX_PITCH_COUNT;
    }

    @Override
    public String toString() {
        return pitchResults.toStringFirstResult() + DELIMITER + SPARE;
    }
}
