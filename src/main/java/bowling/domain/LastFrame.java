package bowling.domain;

import bowling.exception.EndedFrameException;

import java.util.Collections;
import java.util.List;

public class LastFrame extends Frame {

    public static final int FRAME_NUMBER = 10;

    protected LastFrame() {
        super(FRAME_NUMBER);
    }

    @Override
    public void bowl(int fallenPins) {
        if (isEnd()) {
            throw new EndedFrameException();
        }

        if (isNotBonusChance()) {
            super.bowl(fallenPins);
            return;
        }

        results.add(PitchResult.of(fallenPins));
    }

    @Override
    public boolean isEnd() {
        if (hasStrike() || hasSpare()) {
            return results.size() == ATTEMPTS_TO_BOWL_WITH_BONUS;
        }
        return results.size() == ATTEMPTS_TO_BOWL;
    }

    @Override
    public List<PitchResult> results() {
        return Collections.unmodifiableList(results);
    }

    @Override
    public int number() {
        return FRAME_NUMBER;
    }

    private boolean isNotBonusChance() {
        return !hasStrike() && !hasSpare();
    }

}
