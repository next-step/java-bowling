package bowling.domain.state.running;

import bowling.domain.Pins;
import bowling.domain.frame.Score;
import bowling.domain.state.ThrowingState;
import bowling.domain.state.end.Strike;
import bowling.exception.CannotScoreCalculateException;

public class Ready extends RunningState {

    private Ready() {}

    public static ThrowingState create() {
        return new Ready();
    }

    @Override
    public ThrowingState bowl(Pins pins) {
        if (pins.isStrike()) {
            return new Strike();
        }
        return FirstBowl.create(pins);
    }

    @Override
    public String symbol() {
        return "";
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        throw new CannotScoreCalculateException("아직 기회가 남아있어 점수를 확인할 수 없습니다.");
    }
}
