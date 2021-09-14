package bowling.bowlingdrawing.domain.pitching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pitchings {
    public static final int FIRST_PITCHING = 0;

    private final List<Pitching> pitchings = new ArrayList<>();

    public Pitching nextPitching(int pins) {
        if (pitchings.isEmpty()) {
            pitchings.add(Pitching.first(pins));
            return pitchings.get(FIRST_PITCHING);
        }
        Pitching nextPitching = pitchings.get(pitchings.size() - 1).next(pins);
        pitchings.add(nextPitching);
        return nextPitching;
    }

    public List<Pitching> pitchings() {
        return Collections.unmodifiableList(pitchings);
    }
}
