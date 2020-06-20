package bowling.domain.pitch;

import bowling.domain.exception.BowlingBuildingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pitches {
    private static final int FIRST_PITCH_INDEX = 0;
    private static final int SECOND_PITCH_INDEX = 1;
    private static final int STRIKE_PIN_COUNTS = 10;
    private static final int MAXIMUM_PITCH_COUNTS = 2;

    private final List<Pitch> pitches = new ArrayList<>();

    public void recordPitch(int hitCounts) {
        pitches.add(new Pitch(hitCounts));
        if (isFinishingPitches()) {
            validatePitchesSum();
        }
    }

    public boolean isMovableToNextFrame() {
        return isStrike() || isFinishingPitches();
    }

    public boolean isStrike() {
        return pitches.get(FIRST_PITCH_INDEX).getHitCounts() == STRIKE_PIN_COUNTS;
    }

    public boolean isSpare() {
        return pitches.get(FIRST_PITCH_INDEX).getHitCounts() + pitches.get(SECOND_PITCH_INDEX).getHitCounts() == STRIKE_PIN_COUNTS;
    }

    public boolean isMovableToNextPitch() {
        return getPitchCounts() < MAXIMUM_PITCH_COUNTS || getPitchesSum() == STRIKE_PIN_COUNTS;
    }

    private void validatePitchesSum() {
        if (getPitchesSum() > STRIKE_PIN_COUNTS && !isStrike()) {
            throw new BowlingBuildingException();
        }
    }

    public boolean isFinishingPitches() {
        return getPitchCounts() == MAXIMUM_PITCH_COUNTS;
    }

    private int getPitchesSum() {
        return pitches.stream()
                .mapToInt(Pitch::getHitCounts)
                .sum();
    }

    public int getPitchCounts() {
        return pitches.size();
    }

    public List<Pitch> getPitches() {
        return Collections.unmodifiableList(pitches);
    }
}
