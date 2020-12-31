package step2.domain.state;

import step2.domain.frame.Frame;
import step2.domain.Pitch;

import java.util.List;

public class Spare extends Finished {

    private static final String SYMBOL = "/";
    private static final String DELIMITER = "|";

    private Pitch firstPitch;
    private Pitch secondPitch;


    public Spare(Pitch firstPitch, Pitch secondPitch) {
        this.firstPitch = firstPitch;
        this.secondPitch = secondPitch;
    }

    @Override
    public String toString() {
        return firstPitch + DELIMITER + SYMBOL;
    }
}
