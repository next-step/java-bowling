package bowling.domain.state;

import bowling.domain.Pitch;

import java.util.List;

public class Miss extends State {

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
