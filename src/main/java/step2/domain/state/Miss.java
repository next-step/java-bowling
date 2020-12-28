package step2.domain.state;

import step2.domain.Pitch;

public class Miss implements State {

    private static final String DELIMITER = "|";

    private Pitch firstPitch;
    private Pitch secondPitch;

    public Miss(Pitch firstPitch, Pitch secondPitch) {
        this.firstPitch = firstPitch;
        this.secondPitch = secondPitch;
    }

    public Miss(Pitch firstPitch) {
        this.firstPitch = firstPitch;
    }

    @Override
    public String toString() {
        if (secondPitch == null) {
            return "" + firstPitch;
        }
        return firstPitch + DELIMITER + secondPitch;
    }
}
