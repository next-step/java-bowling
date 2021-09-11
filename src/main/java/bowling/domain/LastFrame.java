package bowling.domain;

import bowling.exception.EndedFrameException;

import java.util.Collections;
import java.util.List;

public class LastFrame extends Frame {

    protected LastFrame() {
        super(Frame.LAST_FRAME_NUMBER);
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
        return LAST_FRAME_NUMBER;
    }

    private boolean isNotBonusChance() {
        return !hasStrike() && !hasSpare();
    }

    private boolean hasSpare() {
        return results.stream()
                .anyMatch(PitchResult::isSpare);
    }

}
