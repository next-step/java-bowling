package bowling.domain.state.last;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.Running;
import bowling.domain.state.State;
import bowling.domain.state.normal.Miss;
import bowling.domain.state.normal.Spare;

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
        if (first.isTen() || first.sum(second).isTen()) {
            return new LastFrameSecondBowl(first, second);
        }

        return new LastFrameLastBowl(first, second);
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        return beforeScore.add(first);
    }
}
