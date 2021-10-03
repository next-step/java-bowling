package bowling.domain.state;

import bowling.domain.Left;
import bowling.domain.PinCount;
import bowling.domain.Score;

public class OncePitched extends Playing {
    private final PinCount pinCount;

    public OncePitched(final PinCount pinCount) {
        validateIsNotMaxPinCount(pinCount);
        this.pinCount = pinCount;
    }

    private void validateIsNotMaxPinCount(final PinCount pinCount) {
        if (pinCount.isMaxPinCount()) {
            throw new IllegalArgumentException("OncePitched 상태는 MaxPinCount를 가질 수 없습니다.");
        }
    }

    @Override
    public State play(final PinCount second) {
        if (pinCount.isSpare(second)) {
            return new Spare(pinCount, second);
        }
        return new Miss(pinCount, second);
    }

    @Override
    public Score getScore() {
        return new Score(pinCount.getValue(), Left.ONE);
    }

    @Override
    public String showIndication() {
        return pinCount.showIndication();
    }

    @Override
    public Score calculateBonusScore(final Score score) {
        return score.plusAdditionalPinCount(pinCount);
    }
}
