package bowling.domain.pitchings;

public class LastFramePitchings extends Pitchings {
    private static final int LAST_FRAME_MAX_PITCHING_SIZE = 3;

    public static LastFramePitchings getInstance() {
        return new LastFramePitchings();
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
}
