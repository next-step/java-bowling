package bowling.domain.state.last;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.Running;
import bowling.domain.state.State;
import bowling.exception.CannotCalculateException;
import bowling.utils.StringUtils;

public class LastFrameFirstBowl extends Running {

    private final PinCount first;


    public LastFrameFirstBowl(PinCount first) {
        this.first = first;
    }

    public LastFrameFirstBowl(int count) {
        this(PinCount.of(count));
    }

    @Override
    public State next(PinCount second) {
        if (second.isTen() || first.sum(second).isTen()) {
            return new LastFrameSecondBowl(first, second);
        }

        return new LastFrameMiss(first, second);
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        Score score = beforeScore.add(first);
        if (score.canCalculate()) {
            return score;
        }

        throw new CannotCalculateException();
    }

    @Override
    public String getDesc() {
        return StringUtils.getDesc(first);
    }
}
