package bowling.domain.state.normal;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.Finished;
import bowling.utils.StringUtils;

public class Miss extends Finished {

    private final PinCount first;
    private final PinCount second;

    public Miss(PinCount fist, PinCount second) {
        this.first = fist;
        this.second = second;
    }

    public static Miss from(int first, int second) {
        return new Miss(PinCount.of(first), PinCount.of(second));
    }

    @Override
    public Score getScore() {
        return new Score(first.sum(second).getValue(), 0);
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        if (beforeScore.canCalculate()) {
            return beforeScore;
        }

        beforeScore = beforeScore.add(first);
        if (beforeScore.canCalculate()) {
            return beforeScore;
        }

        return beforeScore.add(second);
    }

    @Override
    public String getDesc() {
        return StringUtils.getDesc(first) + "|" + StringUtils.getDesc(second);
    }
}
