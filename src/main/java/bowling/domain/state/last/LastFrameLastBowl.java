package bowling.domain.state.last;

import bowling.domain.PinCount;
import bowling.domain.Score;
import bowling.domain.state.Finished;
import bowling.domain.state.Running;
import bowling.domain.state.State;
import bowling.domain.state.normal.Miss;
import bowling.domain.state.normal.Spare;

import java.text.FieldPosition;

public class LastFrameLastBowl extends Finished {

    private final PinCount first;
    private final PinCount second;
    private final PinCount last;

    public LastFrameLastBowl(PinCount first, PinCount second, PinCount last) {
        this.first = first;
        this.second = second;
        this.last = last;
    }

    public LastFrameLastBowl(PinCount first, PinCount second) {
        this(first,second, PinCount.of(0));
    }

    public LastFrameLastBowl(int first, int second, int last) {
        this(PinCount.of(first), PinCount.of(second), PinCount.of(last));
    }

    @Override
    public Score calculateBonusScore(Score beforeScore) {
        Score score = beforeScore.add(first).add(second);
        if (last.isZero()) {
            return score;
        }

        return score.add(last);
    }

    @Override
    public Score getScore() {
        return new Score(first.getValue() + second.getValue() + last.getValue(), 0);
    }
}
