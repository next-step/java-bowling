package bowling.domain.state.last;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.Finished;
import bowling.exception.CannotCalculateException;
import bowling.utils.StringUtils;

public class LastFrameMiss extends Finished {

    private final PinCount first;
    private final PinCount second;

    public LastFrameMiss(PinCount first, PinCount second) {
        this.first = first;
        this.second = second;
    }

    public LastFrameMiss(int first, int second) {
        this(PinCount.of(first), PinCount.of(second));
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

    @Override
    public Score getScore() {
        return new Score(first.getValue() + second.getValue(), 0);
    }
}
