package step2.domain.state;

import step2.domain.Pitch;

public class Miss {

    private static final String DELIMITER = "|";

    private final Pitch firstPitch;
    private final Pitch secondPitch;

    public Miss(Pitch firstPitch, Pitch secondPitch) {
        this.firstPitch = firstPitch;
        this.secondPitch = secondPitch;
    }

    @Override
    public String toString() {
        return firstPitch + DELIMITER + secondPitch;
    }
}
