package bowling.domain.pitchings;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;

public class NormalFramePitchings extends Pitchings {
    private static final int NORMAL_FRAME_MAX_PITCHING_SIZE = 2;

    public static NormalFramePitchings getInstance() {
        return new NormalFramePitchings();
    }

    @Override
    public void addPitching(KnockDownPins knockDownPins) {
        if (value.isEmpty()) {
            setFirstPitching(knockDownPins);
            return;
        }

        setSecondPitching(knockDownPins);
    }

    @Override
    public boolean isEnd() {
        if (value.isEmpty()) {
            return false;
        }

        if (isFirstPitchingStrike()) {
            return true;
        }

        return value.size() == NORMAL_FRAME_MAX_PITCHING_SIZE;
    }

    private boolean isFirstPitchingStrike() {
        Pitching firstPitching = value.get(0);
        return firstPitching == Pitching.STRIKE;
    }
}
