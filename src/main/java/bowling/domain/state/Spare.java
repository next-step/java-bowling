package bowling.domain.state;

import bowling.domain.Pitch;

import java.util.List;

public class Spare extends State {

    private static final String SYMBOL = "/";
    private static final String DELIMITER = "|";

    private final Pitch firstPitch;

    public Spare(Pitch firstPitch) {
        this.firstPitch = firstPitch;
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public String toString() {
        return firstPitch + DELIMITER + SYMBOL;
    }
}
