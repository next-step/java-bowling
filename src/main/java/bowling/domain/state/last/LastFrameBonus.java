package bowling.domain.state.last;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.Finished;
import bowling.utils.StringUtils;

public class LastFrameBonus extends Finished {

    private final PinCount first;
    private final PinCount second;
    private final PinCount last;

    public LastFrameBonus(PinCount first, PinCount second, PinCount last) {
        this.first = first;
        this.second = second;
        this.last = last;
    }

    public LastFrameBonus(int first, int second, int last) {
        this(PinCount.of(first), PinCount.of(second), PinCount.of(last));
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

        return score.add(last);
    }

    @Override
    public Score getScore() {
        return new Score(first.getValue() + second.getValue() + last.getValue(), 0);
    }

    @Override
    public String getDesc() {
        return StringUtils.getDesc(first) + "|" + StringUtils.getSecondDesc(first, second) + "|" + StringUtils.getDesc(last);
    }
}
