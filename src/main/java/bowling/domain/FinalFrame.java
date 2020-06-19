package bowling.domain;

import java.util.List;

public class FinalFrame implements Frame {

    private final PitchesGroup pitchesGroup = new PitchesGroup();

    @Override
    public void bowl(int hitCounts) {
        pitchesGroup.recordPitch(hitCounts);
    }

    @Override
    public Frame next() {
        List<Pitch> pitches = pitchesGroup.getPitches();
        if (pitches.size() == 2 && pitches.get(0).getHitCounts() + pitches.get(1).getHitCounts() != 10) {
            return null;
        }
        return this;
    }

    @Override
    public int getScore() {
        List<Pitch> pitches = pitchesGroup.getPitches();
        return pitches.get(pitches.size() - 1).getHitCounts();
    }

    @Override
    public PitchesGroup getPitchesGroup() {
        return pitchesGroup;
    }
}
