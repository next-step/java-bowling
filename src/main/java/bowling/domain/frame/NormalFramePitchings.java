package bowling.domain.frame;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;

import java.util.ArrayList;
import java.util.List;

class NormalFramePitchings {
    private static final int NORMAL_FRAME_MAX_PITCHING_SIZE = 2;
    private final List<Pitching> value;

    public NormalFramePitchings() {
        this.value = new ArrayList<>();
    }

    public static NormalFramePitchings getInstance() {
        return new NormalFramePitchings();
    }

    void addPitching(KnockDownPins knockDownPins) {
        if (value.isEmpty()) {
            setFirstPitching(knockDownPins);
            return;
        }

        setSecondPitching(knockDownPins);
    }

    private void setFirstPitching(KnockDownPins knockDownPins) {
        Pitching pitching = Pitching.getPitching(knockDownPins);
        value.add(pitching);
    }

    private void setSecondPitching(KnockDownPins knockDownPins) {
        int lastIndex = value.size() - 1;
        Pitching previousPitching = value.get(lastIndex);
        Pitching pitching = Pitching.getPitching(knockDownPins, previousPitching);
        value.add(pitching);
    }

    boolean isEnd() {
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

    public List<Pitching> getValue() {
        return value;
    }
}
