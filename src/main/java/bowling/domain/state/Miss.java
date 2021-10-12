package bowling.domain.state;

import bowling.domain.Left;
import bowling.domain.PinCount;
import bowling.domain.Score;

public class Miss extends Finished {
    private final PinCount first;

    private final PinCount second;

    public Miss(final PinCount first, final PinCount second) {
        validateSum(first, second);
        this.first = first;
        this.second = second;
    }

    private void validateSum(final PinCount first, final PinCount second) {
        PinCount sum = first.plus(second);
        if (sum.isMaxPinCount()) {
            throw new IllegalArgumentException("Miss 상태는 first, second 합이 " + PinCount.MAX + "를 가질 수 없습니다.");
        }
    }

    @Override
    public String showIndication() {
        return first.showIndication(second);
    }

    @Override
    public Score getScore() {
        PinCount sum = first.plus(second);
        return new Score(sum.getValue(), Left.ZERO);
    }

    @Override
    public Score calculateBonusScore(final Score beforeScore) {
        Score score = beforeScore.plusAdditionalPinCount(first);
        if (!score.canCalculate()) {
            return score.plusAdditionalPinCount(second);
        }
        return score;
    }
}
