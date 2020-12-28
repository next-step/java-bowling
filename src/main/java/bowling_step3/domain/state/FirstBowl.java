package bowling_step3.domain.state;

import bowling_step3.domain.Pitch;

public class FirstBowl extends Running {
    private final Pitch firstPitch;

    public FirstBowl(int firstCountOfKnockDown) {
        this.firstPitch = Pitch.valueOf(firstCountOfKnockDown);
    }

    private boolean isSpare(int countOfKnockDown) {
        return firstPitch.isSpare(Pitch.valueOf(countOfKnockDown));
    }
    @Override
    public State pitch(int countOfKnockDown) {
        if (isSpare(countOfKnockDown)) {
            return new Spare(firstPitch, Pitch.valueOf(countOfKnockDown));
        }

        return new Miss(firstPitch, Pitch.valueOf(countOfKnockDown));
    }

    @Override
    public int getPitchCount() {
        return 1;
    }

    @Override
    public int getTotalCount() {
        return firstPitch.getKnockDown();
    }

    @Override
    public String toString() {
        return firstPitch.toString();
    }
}
