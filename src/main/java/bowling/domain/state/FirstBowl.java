package bowling.domain.state;


import bowling.domain.PitchResults;

public class FirstBowl extends Running{

    public FirstBowl(PitchResults pitchResults){
        this.pitchResults = pitchResults;
    }

    @Override
    public State pitch(int secondKnockedDownPins) {
        addPitchResult(secondKnockedDownPins);

        if (pitchResults.isSpare()) {
            return new Spare(pitchResults);
        }

        return new Miss(pitchResults);
    }

    @Override
    public int getPitchTryCount() {
        return MAX_PITCH_COUNT;
    }

    @Override
    public String toString() {
        return pitchResults.toStringFirstResult();
    }
}
