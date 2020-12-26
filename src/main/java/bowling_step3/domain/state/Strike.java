package bowling_step3.domain.state;

import bowling_step3.domain.Score;

public class Strike extends Finished {
    private static final String STRIKE = "X";
    private static final int MIN_PITCH_COUNT = 1;
    private static final int BOWLING_MAX_TOTAL = 10;

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
        return Score.ofStrike();
    }

    @Override
    public int getTotalCount() {
        return BOWLING_MAX_TOTAL;
    }
}
