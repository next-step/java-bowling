package bowling.domain;

import bowling.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Pitches {

    private static final int MAXIMUM_NORMAL_FRAME_PITCH = 2;
    private static final int FIRST_PITCH_INDEX = 0;
    private static final int FIRST_PITCH_SIZE = 1;

    private List<Pitch> pitches = new ArrayList<>();

    public boolean isEmpty() {
        return pitches.isEmpty();
    }

    public void add(final Pitch pitch) {
        pitches.add(pitch);
    }

    public boolean equalsToSize(final int size) {
        return pitches.size() == size;
    }

    public Pitch get(final int index) {
        return pitches.get(index);
    }

    public List<Pitch> value() {
        return pitches;
    }

    public int size() {
        return pitches.size();
    }

    boolean firstStrike() {
        return pitches.size() == FIRST_PITCH_SIZE && pitches.get(FIRST_PITCH_INDEX).isStrike();
    }

    public boolean isMaxSize() {
        return pitches.size() == MAXIMUM_NORMAL_FRAME_PITCH;
    }

    public List<Status> statuses() {
        return pitches.stream()
                .map(Pitch::status)
                .collect(Collectors.toList());
    }

    public int sum() {
        return pitches
                .stream()
                .map(Pitch::intValue)
                .reduce(0, Integer::sum);
    }

    public boolean isLastPitchStatus(Status status) {
        return pitches.get(pitches.size() - 1).status().equals(status);
    }

    public void validateNormalSecondPitch(int countOfPins) {
        if (pitches.isEmpty()) {
            return;
        }
        if (pitches.get(0).intValue() + countOfPins > Pitch.MAXIMUM_COUNT_OF_PINS) {
            throw new BusinessException("일반 투구의 합계는 10이하여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pitches pitches1 = (Pitches) o;
        return Objects.equals(pitches, pitches1.pitches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pitches);
    }

}
