package bowling.domain.state.progress;

import bowling.domain.exception.CannotCalculateException;
import bowling.domain.state.BowlingPin;
import bowling.domain.state.Progress;
import bowling.domain.state.State;
import bowling.domain.state.result.Strike;

public class Ready implements Progress {

    private static final String SYMBOL = "";

    public Ready(){
    }

    @Override
    public State bowl(BowlingPin bowlingPin) {
        if (bowlingPin.isMax()) {
            return Strike.of(bowlingPin);
        }
        return Running.of(bowlingPin);
    }

    @Override
    public String toSymbol() {
        return SYMBOL;
    }

    @Override
    public int currentBowlingPin() {
        throw new CannotCalculateException("점수를 계산할 수 없는 상태입니다.");
    }
}
