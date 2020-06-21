package bowling.domain.pitch;

import bowling.domain.bowling.BowlingPinsGroup;

import java.util.ArrayList;
import java.util.List;

public class FinalPitches implements Pitches {

    private final List<Pitch> pitches = new ArrayList<>();

    public void throwBall(int hitCounts, BowlingPinsGroup bowlingPinsGroup) {
        Pitch pitch = new Pitch(hitCounts);
        pitch.throwBall(bowlingPinsGroup);
        pitches.add(pitch);
    }

    public boolean isAvailableToPitchBonus() {
        boolean isContainingStrike = pitches.stream()
                .anyMatch(Pitch::isStrike);
        boolean isSpareOrAllStrike = pitches.stream()
                .mapToInt(Pitch::getHitCounts)
                .sum() % 10 == 0;
        return isContainingStrike || isSpareOrAllStrike;
    }

    @Override
    public int getPitchCounts() {
        return pitches.size();
    }

    public int getPitchesSum() {
        return pitches.stream()
                .mapToInt(Pitch::getHitCounts)
                .sum();
    }
}
