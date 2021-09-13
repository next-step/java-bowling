package bowling.bowlingdrawing.domain.pitching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pitchings {

    private final List<Pitching> pitchings = new ArrayList<>();

    public Pitching nextPitching(int pins) {
        if (pitchings.isEmpty()) {
            pitchings.add(Pitching.first(pins));
            return pitchings.get(0);
        }
        Pitching nextPitching = pitchings.get(pitchings.size() - 1).next(pins);
        pitchings.add(nextPitching);
        return nextPitching;
    }

    public List<Pitching> pitchings() {
        return Collections.unmodifiableList(pitchings);
    }
}
