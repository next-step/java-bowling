package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class Ready extends RunningState {
    private static final String READY_SYMBOL = "";

    private Ready() {
    }

    public static ThrowingState create() {
        return new Ready();
    }

    @Override
    public ThrowingState bowl(Pins pins) {
        if (pins.isStrike()) {
            return Strike.create();
        }
        return FirstBowl.create(pins);
    }

    @Override
    public String symbol() {
        return READY_SYMBOL;
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    @Override
    public Score score() {
        return Score.withReady();
    }

    @Override
    public Score scoreAfter(Score previousScore) {
        throw new IllegalStateException("이전 점수가 없는 준비 상태입니다.");
    }
}
