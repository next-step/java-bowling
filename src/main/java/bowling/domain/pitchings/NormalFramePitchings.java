package bowling.domain.pitchings;

import bowling.domain.Score;

import java.util.function.BiFunction;

public class NormalFramePitchings extends Pitchings {
    private static final int NORMAL_FRAME_MAX_PITCHING_SIZE = 2;

    public static NormalFramePitchings getInstance() {
        return new NormalFramePitchings();
    }

    @Override
    public boolean isEnd() {
        if (value.isEmpty()) {
            return false;
        }

        if (isStrike()) {
            return true;
        }

        return value.size() == NORMAL_FRAME_MAX_PITCHING_SIZE;
    }

    public BiFunction<Integer, Score, Integer> calculateTotalScore() {
        return (previousFrameTotalScore, score) -> {
            if (canNotCalculateTotalScore(previousFrameTotalScore, score)) {
                return null;
            }

            if (score.leftBonusApplyChance()) {
                return null;
            }

            return previousFrameTotalScore + score.getValue();
        };
    }
}
