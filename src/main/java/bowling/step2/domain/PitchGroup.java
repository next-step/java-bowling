package bowling.step2.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        return total() % 10;
    }

    public int total() {
        return pitches.stream()
                .mapToInt(Pitch::count)
                .sum();
    }

    public List<Integer> pitches() {
        return pitches.stream()
                .map(Pitch::count)
                .collect(Collectors.toList());
    }
}
