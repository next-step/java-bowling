package bowling_step3.domain.state;

import bowling_step3.domain.Pitch;
import bowling_step3.domain.Score;

import static bowling_step3.domain.Pitch.BOWLING_MAX_NUMBER;
import static bowling_step3.domain.state.Miss.MAX_PITCH_COUNT;

public class Spare extends Finished {

    public Spare(Pitch firstPitch, Pitch secondPitch) {
        this.firstPitch = firstPitch;
        this.secondPitch = secondPitch;
    }

    @Override
    public int getPitchCount() {
        return MAX_PITCH_COUNT;
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
    }

    @Override
    public int getTotalCount() {
        return BOWLING_MAX_NUMBER;
    }

    @Override
    public String toString() {
        return firstPitch.toString() + "|/";
    }
}
