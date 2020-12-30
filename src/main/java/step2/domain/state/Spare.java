package step2.domain.state;

import step2.domain.frame.Frame;
import step2.domain.Pitch;

import java.util.List;

public class Spare implements State {

    private static final String SYMBOL = "/";
    private static final String DELIMITER = "|";

    private final List<Pitch> pitches;

    public Spare(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    @Override
    public String toString() {
        if (pitches.size() == Frame.MAX_SIZE) {
            return pitches.get(0) + DELIMITER + pitches.get(1) + DELIMITER + SYMBOL;
        }
        return pitches.get(0) + DELIMITER + SYMBOL;
    }
}
