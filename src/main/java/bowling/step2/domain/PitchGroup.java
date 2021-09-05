package bowling.step2.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PitchGroup {
    private final List<Pitch> pitches;

    private static final int MAX_PITCH_TOTAL_COUNT = 10;

    private static final int NORMAL_PITCH_SIZE = 2;

    private final int MAX_PITCH_SIZE;

    private PitchGroup(int max_pitch_size) {
        MAX_PITCH_SIZE = max_pitch_size;
        this.pitches = new ArrayList<>();
    }

    public static PitchGroup of(int maxPitchSize) {
        return new PitchGroup(maxPitchSize);
    }

    public void pitch(int count) {
        validatePitchCount(lastPitchCount(), count);
        validateAdditionalPitch();
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

    private void validateAdditionalPitch() {
        if (pitchGroupSizeIsTwo() && totalCountIsLessThanMax()) {
            throw new RuntimeException("더이상 공을 던질 수 없습니다.");
        }
    }

    private boolean totalCountIsLessThanMax() {
        return total() < MAX_PITCH_TOTAL_COUNT;
    }

    private boolean pitchGroupSizeIsTwo() {
        return size() == NORMAL_PITCH_SIZE;
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

    public boolean finished() {
        return (pitchGroupSizeIsTwo() && totalCountIsLessThanMax()) || size() == MAX_PITCH_SIZE;
    }
}
