package bowling.domain.pitchings;

public class LastFramePitchings2 extends Pitchings2 {
    private static final int LAST_FRAME_MAX_PITCHING_SIZE = 3;

    public static LastFramePitchings2 getInstance() {
        return new LastFramePitchings2();
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
