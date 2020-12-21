package bowling.domain.pitchings;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;
import bowling.domain.Score;

import java.util.function.BiFunction;

public class LastFramePitchings extends Pitchings {
    private static final int LAST_FRAME_MAX_PITCHING_SIZE = 3;

    public static LastFramePitchings getInstance() {
        return new LastFramePitchings();
    }

    @Override
    Score renewScore(Pitching pitching) {
        int currentScore = getPitchingScore(pitching);
        return score.addScore(currentScore);
    }

    private int getPitchingScore(Pitching pitching) {
        if (pitching == Pitching.SPARE) {
            int previousIndex = value.size() - 2;
            Pitching previousPitching = value.get(previousIndex);
            return KnockDownPins.MAX_VALUE - previousPitching.getScore();
        }
        return pitching.getScore();
    }

    @Override
    public boolean isEnd() {
        if (hasPitchingChance()) {
            return false;
        }

        if (hasThirdPitchingChance()) {
            return value.size() == LAST_FRAME_MAX_PITCHING_SIZE;
        }

        return true;
    }

    private boolean hasPitchingChance() {
        return value.size() < LAST_FRAME_MAX_PITCHING_SIZE - 1;
    }

    private boolean hasThirdPitchingChance() {
        return isStrike() || isSpare();
    }

    @Override
    public BiFunction<Integer, Score, Integer> calculateTotalScore() {
        return (previousFrameTotalScore, score) -> {
            if (canNotCalculateTotalScore(previousFrameTotalScore, score)) {
                return null;
            }

            return previousFrameTotalScore + score.getValue();
        };
    }
}
