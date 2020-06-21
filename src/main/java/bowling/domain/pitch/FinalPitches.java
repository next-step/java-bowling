package bowling.domain.pitch;

import java.util.ArrayList;
import java.util.List;

public class FinalPitches {

    private final List<Pitch> pitches = new ArrayList<>();

    public void recordPitch(int hitCounts) {
        pitches.add(new Pitch(hitCounts));
    }

    public int getPitchCounts() {
        return pitches.size();
    }

    public boolean isStrike() {
        return pitches.get(0).isStrike();
    }

    public boolean isSpare() {
        return pitches.get(0).getHitCounts() + pitches.get(1).getHitCounts() == 10;
    }
}
