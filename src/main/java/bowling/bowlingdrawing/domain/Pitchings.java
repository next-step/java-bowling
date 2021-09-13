package bowling.bowlingdrawing.domain;

import java.util.ArrayList;
import java.util.List;

public class Pitchings {

    private final List<Pitching> pitchings = new ArrayList<>();

    public Pitching nextPitching(int pins) {
        if (pitchings.isEmpty()) {
            return Pitching.first(pins);
        }
        return pitchings.get(pitchings.size() - 1).next(pins);
    }

}
