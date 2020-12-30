package step2.domain.state;

import step2.domain.Frame;
import step2.domain.Pitch;

import java.util.List;

public class Miss implements State {

    private static final String DELIMITER = "|";

    private List<Pitch> pitches;

    public Miss(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    @Override
    public String toString() {
        if (pitches.size() == Frame.MAX_SIZE) {
            return "" + pitches.get(0) + DELIMITER + pitches.get(1) + DELIMITER + pitches.get(2);
        }

        if (pitches.size() == Frame.NORMAL_FRAME_SIZE) {
            return "" + pitches.get(0) + DELIMITER + pitches.get(1);
        }

        return "" + pitches.get(0);
    }
}
