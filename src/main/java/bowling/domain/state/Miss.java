package bowling.domain.state;

import bowling.domain.KnockedPinCount;
import bowling.domain.Score;

import java.util.List;

public class Miss extends AbstractFinished {
    public Miss(KnockedPinCount pinCount, KnockedPinCount newPinCount) {
        super(pinCount, newPinCount);
    }

    @Override
    public State bowl(int pinCount) {
        throw new IllegalArgumentException("Miss에선 추가 투구를 할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean hasBonus() {
        return false;
    }

    @Override
    public Score makeScore() {
        List<KnockedPinCount> knockedPinCounts = this.knockedPinCounts.getValues();
        return new Score(knockedPinCounts.get(ZERO).value() + knockedPinCounts.get(ONE).value(), 0);
    }

    @Override
    public Score additionalCalculate(Score beforeScore) {
        Score score = beforeScore.bowl(getValues().get(0).value());
        if (score.canCalculateScore()) {
            return score;
        }
        return score.bowl(getValues().get(1).value());
    }
}
