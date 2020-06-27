package bowling.domain.score;

import bowling.domain.exception.BowlingBuildingException;

import java.util.HashMap;
import java.util.Map;

public class PitchScore {
    private static final Map<Integer, PitchScore> CACHE = new HashMap<>();
    private static final int MINIMUM_SCORE = 0;
    private static final int MAXIMUM_SCORE = 10;

    private final int pitchScore;

    private PitchScore(int pitchScore) {
        this.pitchScore = pitchScore;
    }

    public static PitchScore valueOf(int pitchScore) {
        validatePitchScore(pitchScore);
        return CACHE.computeIfAbsent(pitchScore, PitchScore::new);
    }

    private static void validatePitchScore(int pitchScore) {
        if (pitchScore < MINIMUM_SCORE || pitchScore > MAXIMUM_SCORE) {
            throw new BowlingBuildingException(BowlingBuildingException.INVALID_PITCH_SCORE_RANGE);
        }
    }

    public boolean isMaximum() {
        return pitchScore == MAXIMUM_SCORE;
    }

    public boolean isMinimum() {
        return pitchScore == MINIMUM_SCORE;
    }

    public int getPitchScore() {
        return pitchScore;
    }
}
