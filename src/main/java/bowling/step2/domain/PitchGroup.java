package bowling.step2.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PitchGroup {
    private final List<Pitch> pitches;

    private static final int MAX_PITCH_TOTAL_COUNT = 10;

    private PitchGroup() {
        this.pitches = new ArrayList<>();
    }

    public static PitchGroup of() {
        return new PitchGroup();
    }

    public void pitch(int count) {
        validatePitchCount(lastPitchCount(), count);
        pitches.add(Pitch.of(count));
    }

    private void validatePitchCount(int lastPitchCount, int count) {
        if (sumWithLastPitchOverTheMax(lastPitchCount, count)) {
            throw new RuntimeException("쓰러뜨릴 수 있는 핀의 갯수를 넘어섰습니다.");
        }
    }

    private boolean sumWithLastPitchOverTheMax(int lastPitchCount, int count) {
        return lastPitchCount + count > MAX_PITCH_TOTAL_COUNT;
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
