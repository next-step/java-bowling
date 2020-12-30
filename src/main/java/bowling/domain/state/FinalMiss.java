package bowling.domain.state;

import bowling.domain.Pitch;

public class FinalMiss extends State {
    private static final String DELIMITER = "|";

    private final Pitch firstPitch;
    private final Pitch secondPitch;

    public FinalMiss(Pitch firstPitch, Pitch secondPitch) {
        this.firstPitch = firstPitch;
        this.secondPitch = secondPitch;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public String toString() {
        return firstPitch + DELIMITER + secondPitch;
    }
}
