package bowling_step3.domain.state;

import bowling_step3.domain.Pitch;
import bowling_step3.domain.Score;

public class Spare extends Finished {

    private static final String VERTICAL_SPARE = "|/";

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
        return BOWLING_MAX_TOTAL;
    }

    @Override
    public String toString() {
        return firstPitch.toString() + VERTICAL_SPARE;
    }
}
