package bowling.domain.state;

import bowling.domain.PitchResults;
import bowling.domain.Score;

import java.util.stream.Collectors;

public class Miss extends Finished{

    public Miss(PitchResults pitchResults){
        this.pitchResults = pitchResults;
    }

    @Override
    public Score createScore(int previousScore) {

        int sumUp = previousScore + sumUpCurrentResult();

        return Score.of(sumUp);
    }

    @Override
    public int getPitchTryCount() {
        return MAX_PITCH_COUNT;
    }

    @Override
    public String toString() {
        return pitchResults.getPitchResults()
                .stream()
                .map(pitchResult -> pitchResult.toString())
                .collect(Collectors.joining(DELIMITER));
    }
}
