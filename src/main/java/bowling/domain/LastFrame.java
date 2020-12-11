package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class LastFrame implements Frame {
    public static final String INVALID_PITCHING_CHANCE_ERR_MSG = "투구 기회가 없습니다.";
    private static final int LAST_FRAME_MAX_PITCHING_SIZE = 3;
    private final int index;
    private final List<Pitching> pitchings;

    private LastFrame(int index) {
        this.index = index;
        pitchings = new ArrayList<>();
    }

    public static Frame getInstance(int index) {
        return new LastFrame(index);
    }

    @Override
    public Frame initNextFrame() {
        throw new IllegalStateException();
    }

    @Override
    public Frame getNextFrame() {
        throw new IllegalStateException();
    }

    @Override
    public void setKnockDownPins(KnockDownPins knockDownPins) {
        if (pitchings.isEmpty()) {
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
        pitchings.add(pitching);
    }

    private boolean isSecondPitchingSequence() {
        return pitchings.size() == 1;
    }

    private void setSecondPitching(KnockDownPins knockDownPins) {
        int lastIndex = pitchings.size() - 1;
        Pitching previousPitching = pitchings.get(lastIndex);
        Pitching pitching = Pitching.getPitching(knockDownPins, previousPitching);
        pitchings.add(pitching);
    }

    private boolean hasThirdPitchingChance() {
        return containsStrike() || containsSpare();
    }

    private boolean containsStrike() {
        Pitching strike = Pitching.STRIKE;
        return pitchings.contains(strike);
    }

    private boolean containsSpare() {
        Pitching spare = Pitching.SPARE;
        return pitchings.contains(spare);
    }

    private void setThirdPitching(KnockDownPins knockDownPins) {
        int lastIndex = pitchings.size() - 1;
        Pitching previousPitching = pitchings.get(lastIndex);
        pitchings.add(Pitching.getPitching(knockDownPins, previousPitching));
    }

    @Override
    public List<Pitching> getPitchings() {
        return new ArrayList<>(pitchings);
    }

    @Override
    public boolean isEnd() {
        if (hasPitchingChance()) {
            return false;
        }

        if (hasThirdPitchingChance()) {
            return pitchings.size() == LAST_FRAME_MAX_PITCHING_SIZE;
        }

        return true;
    }

    private boolean hasPitchingChance() {
        return pitchings.size() < LAST_FRAME_MAX_PITCHING_SIZE - 1;
    }

    @Override
    public boolean isLastFrame() {
        return true;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "LastFrame{" +
                "index=" + index +
                ", pitchings=" + pitchings +
                '}';
    }
}
