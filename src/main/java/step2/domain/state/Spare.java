package step2.domain.state;

import step2.domain.Pitch;

public class Spare {

    private static final String SYMBOL = "/";
    private static final String DELIMITER = "|";

    private final Pitch firstPitch;

    public Spare(Pitch firstPitch) {
        this.firstPitch = firstPitch;
    }

    @Override
    public String toString() {
        return firstPitch + DELIMITER + SYMBOL;
    }
}
