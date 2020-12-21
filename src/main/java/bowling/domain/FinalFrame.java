package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame implements Frame {
    public static final int MAXIMUM_PITCH = 3;

    private List<Pitch> pitches;

    private FinalFrame(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public static FinalFrame init() {
        List<Pitch> pitches = new ArrayList<>(MAXIMUM_PITCH);
        return new FinalFrame(pitches);
    }

    public Frame add(Pitch pitch) {
        addSecondPitch(pitch);
        addFirstPitch(pitch);
        return new FinalFrame(pitches);
    }

    private void addFirstPitch(Pitch pitch) {
        if(pitches.isEmpty()) {
            pitches.add(pitch);
        }
    }

    private void addSecondPitch(Pitch pitch) {
        if(!pitches.isEmpty()) {
            pitches.add(pitch);
        }
    }

    public int getPitchSize() {
        return pitches.size();
    }
}
