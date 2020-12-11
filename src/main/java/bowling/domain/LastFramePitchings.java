package bowling.domain;

import java.util.ArrayList;
import java.util.List;

class LastFramePitchings {
    private static final String INVALID_PITCHING_CHANCE_ERR_MSG = "투구 기회가 없습니다.";
    private static final int LAST_FRAME_MAX_PITCHING_SIZE = 3;
    private final List<Pitching> value;

    public LastFramePitchings() {
        this.value = new ArrayList<>();
    }

    public static LastFramePitchings getInstance() {
        return new LastFramePitchings();
    }

    void addPitching(KnockDownPins knockDownPins) {
        if (value.isEmpty()) {
            setFirstPitching(knockDownPins);
            return;
        }

        if (isSecondPitchingSequence()) {
            setSecondPitching(knockDownPins);
            return;
        }

        if (hasThirdPitchingChance()) {
            setThirdPitching(knockDownPins);
            return;
        }

        throw new IllegalStateException(INVALID_PITCHING_CHANCE_ERR_MSG);
    }

    private void setFirstPitching(KnockDownPins knockDownPins) {
        Pitching pitching = Pitching.getPitching(knockDownPins);
        value.add(pitching);
    }

    private boolean isSecondPitchingSequence() {
        return value.size() == 1;
    }

    private void setSecondPitching(KnockDownPins knockDownPins) {
        int lastIndex = value.size() - 1;
        Pitching previousPitching = value.get(lastIndex);
        Pitching pitching = Pitching.getPitching(knockDownPins, previousPitching);
        value.add(pitching);
    }

    private boolean hasThirdPitchingChance() {
        return containsStrike() || containsSpare();
    }

    private boolean containsStrike() {
        Pitching strike = Pitching.STRIKE;
        return value.contains(strike);
    }

    private boolean containsSpare() {
        Pitching spare = Pitching.SPARE;
        return value.contains(spare);
    }

    private void setThirdPitching(KnockDownPins knockDownPins) {
        int lastIndex = value.size() - 1;
        Pitching previousPitching = value.get(lastIndex);
        value.add(Pitching.getPitching(knockDownPins, previousPitching));
    }

    boolean isEnd() {
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

    public List<Pitching> getValue() {
        return value;
    }
}
