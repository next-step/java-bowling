package bowling.domain.state.last;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.Running;
import bowling.domain.state.State;
import bowling.domain.state.normal.Miss;
import bowling.domain.state.normal.Spare;

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
        return new LastFrameLastBowl(first, second, last);
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        return beforeScore.add(first).add(second);
    }
}
