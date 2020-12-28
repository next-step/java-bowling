package step2.domain.state;

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
        return pitches.get(0) + DELIMITER + SYMBOL;
    }
}
