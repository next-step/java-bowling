package bowling_step3.domain.state;

import bowling_step3.domain.Pitch;
import bowling_step3.domain.Score;
import bowling_step3.exception.BowlingMaxCountException;

import static bowling_step3.domain.Pitch.BOWLING_MAX_NUMBER;

public class Miss extends Finished {
    private final Pitch firstPitch;
    private final Pitch secondPitch;

    public Miss(int firstPitch, int secondPitch) {
        this(Pitch.valueOf(firstPitch), Pitch.valueOf(secondPitch));
    }

    public Miss(Pitch firstPitch, Pitch secondPitch) {
        this.validate(firstPitch, secondPitch);

        this.firstPitch = firstPitch;
        this.secondPitch = secondPitch;
    }

    private boolean isOverPitch(int firstOfKnockDown, int secondOfKnockDown) {
        return firstPitch.getKnockDown() + secondPitch.getKnockDown() >= BOWLING_MAX_NUMBER;
    }

    private void validate(Pitch firstPitch, Pitch secondPitch) {
        if (isOverPitch(firstPitch.getKnockDown(), secondPitch.getKnockDown())) {
            throw new BowlingMaxCountException();
        }
    }

    @Override
    public int getPitchCount() {
        return 2;
    }

    @Override
    public Score getScore() {
        return Score.ofMiss(firstPitch.sum(secondPitch));
    }

    @Override
    public int getTotalCount() {
        return firstPitch.sum(secondPitch);
    }

    @Override
    public String toString() {
        return firstPitch.toString()
                + "|"
                + (firstPitch.sum(secondPitch) == 0 ? "-" : secondPitch.toString());
    }
}
