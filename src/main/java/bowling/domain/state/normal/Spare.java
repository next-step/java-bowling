package bowling.domain.state.normal;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.Finished;
import bowling.utils.StringUtils;

public class Spare extends Finished {

    private final PinCount first;
    private final PinCount second;

    public Spare(int first, int second) {
        this(PinCount.of(first), PinCount.of(second));
    }

    public Spare(PinCount first, PinCount second) {
        if (!first.sum(second).isTen()) {
            throw new IllegalArgumentException("합은 10이어야 합니다");
        }

        this.first = first;
        this.second = second;
    }

    @Override
    public Score getScore() {
        return new Score(PinCount.MAX, 1);
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
        return StringUtils.getDesc(first) + "|/";
    }
}
