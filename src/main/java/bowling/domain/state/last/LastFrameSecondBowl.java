package bowling.domain.state.last;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.Running;
import bowling.domain.state.State;
import bowling.exception.CannotCalculateException;
import bowling.utils.StringUtils;

public class LastFrameSecondBowl extends Running {

    private final PinCount first;
    private final PinCount second;

    public LastFrameSecondBowl(PinCount first, PinCount second) {
        this.first = first;
        this.second = second;
    }

    public LastFrameSecondBowl(int first, int second) {
        this(PinCount.of(first), PinCount.of(second));
    }

    @Override
    public State next(PinCount last) {
        return new LastFrameBonus(first, second, last);
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        Score score = beforeScore.add(first);
        if (score.canCalculate()) {
            return score;
        }
        score = score.add(second);
        if (score.canCalculate()) {
            return score;
        }
        throw new CannotCalculateException();
    }

    @Override
    public String getDesc() {
        return StringUtils.getDesc(first) + "|" + StringUtils.getSecondDesc(first, second);
    }
}
