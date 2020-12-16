package bowling.domain.pitchings;

import bowling.domain.KnockDownPins;
import bowling.domain.Pitching;

import java.util.Optional;

public class LastFramePitchings extends Pitchings {
    private static final String INVALID_PITCHING_CHANCE_ERR_MSG = "투구 기회가 없습니다.";
    private static final int LAST_FRAME_MAX_PITCHING_SIZE = 3;

    public static LastFramePitchings getInstance() {
        return new LastFramePitchings();
    }

    @Override
    public void addPitching(KnockDownPins knockDownPins) {
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

    private boolean isSecondPitchingSequence() {
        return value.size() == 1;
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

    @Override
    public Optional<Integer> getTotalScoreWithStrikeBonus(Optional<Pitching> nextPitching, Optional<Pitching> nextAndNextPitching) {
        throw new IllegalStateException();
    }

    @Override
    public Optional<Integer> calculateTotalScoreWithSpareBonus(Optional<Pitching> nextPitching) {
        throw new IllegalStateException();
    }
}
