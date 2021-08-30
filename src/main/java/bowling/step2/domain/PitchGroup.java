package bowling.step2.domain;

import java.util.ArrayList;
import java.util.List;

public class PitchGroup {
    private final List<Pitch> pitches;

    private PitchGroup() {
        this.pitches = new ArrayList<>();
    }

    public static PitchGroup of() {
        return new PitchGroup();
    }

    public void pitch(int count) {
        pitches.add(Pitch.of(count));
    }

    public int size() {
        return pitches.size();
    }

    public int lastPitchCount() {
        if (pitches.isEmpty()) {
            return 0;
        }

        return pitches.get(pitches.size() - 1)
                .count() % 10;
    }

    public int total() {
        return pitches.stream()
                .mapToInt(Pitch::count)
                .sum();
    }
}
