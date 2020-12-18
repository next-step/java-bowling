package bowling.domain.pitchings;

public class NormalFramePitchings2 extends Pitchings2 {
    private static final int NORMAL_FRAME_MAX_PITCHING_SIZE = 2;

    public static NormalFramePitchings2 getInstance() {
        return new NormalFramePitchings2();
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
