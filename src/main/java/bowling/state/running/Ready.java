package bowling.state.running;

import bowling.Pins;
import bowling.exception.CannotScoreCalculateException;
import bowling.frame.Score;
import bowling.state.Throwing;
import bowling.state.ended.Strike;

public class Ready extends Running {

    @Override
    public Throwing bowl(Pins fallenPins) {
        if (fallenPins.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(fallenPins);
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
