package bowling.domain.state.normal;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.Running;
import bowling.domain.state.State;
import bowling.utils.StringUtils;

public class FirstBowl extends Running {

    private final PinCount first;

    public FirstBowl(PinCount first) {
        this.first = first;
    }

    @Override
    public State next(PinCount second) {
        if (first.sum(second).isTen()) {
            return new Spare(first, second);
        }

        return new Miss(first, second);
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        return beforeScore.add(first);
    }

    @Override
    public String getDesc() {
        return StringUtils.getDesc(first) + "|";
    }
}
