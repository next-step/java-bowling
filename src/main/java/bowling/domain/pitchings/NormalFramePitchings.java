package bowling.domain.pitchings;

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
}
