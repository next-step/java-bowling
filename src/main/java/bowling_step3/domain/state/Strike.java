package bowling_step3.domain.state;

import bowling_step3.domain.Score;

import static bowling_step3.domain.Pitch.BOWLING_MAX_NUMBER;

public class Strike extends Finished {
    private static final int COUNT_OF_STRIKE = 2;
    private static final String STRIKE = "X";

    @Override
    public String toString() {
        return STRIKE;
    }

    @Override
    public int getPitchCount() {
        return MIN_PITCH_COUNT;
    }

    @Override
    public Score getScore() {
        return Score.ofKnockDownState(BOWLING_MAX_NUMBER, COUNT_OF_STRIKE);
    }

    @Override
    public int getTotalCount() {
        return BOWLING_MAX_TOTAL;
    }
}
