package bowling_step3.domain.state;

import bowling_step3.domain.Pitch;
import bowling_step3.domain.Score;
import bowling_step3.exception.BowlingMaxCountException;

public class Miss extends Finished {
    private static final int DEFAULT = 0;

    public Miss(Pitch firstPitch, Pitch secondPitch) {
        validate(firstPitch, secondPitch);
        this.firstPitch = firstPitch;
        this.secondPitch = secondPitch;
    }

    private boolean isOverPitch(int firstOfKnockDown, int secondOfKnockDown) {
        return firstOfKnockDown + secondOfKnockDown >= BOWLING_MAX_TOTAL;
    }

    private void validate(Pitch firstPitch, Pitch secondPitch) {
        if (isOverPitch(firstPitch.getKnockDown(), secondPitch.getKnockDown())) {
            throw new BowlingMaxCountException();
        }
    }

    @Override
    public int getPitchCount() {
        return MAX_PITCH_COUNT;
    }

    @Override
    public Score getScore() {
        return Score.ofKnockDownState(getTotalCount(), DEFAULT);
    }

    @Override
    public int getTotalCount() {
        return firstPitch.sum(secondPitch);
    }

    @Override
    public String toString() {
        return firstPitch.toString() +
                "|" +
                (firstPitch.sum(secondPitch) == 0 ? "-" : secondPitch.toString());
    }
}
