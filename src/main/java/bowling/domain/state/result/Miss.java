package bowling.domain.state.result;

import bowling.domain.score.Score;
import bowling.domain.state.BowlingPin;
import bowling.domain.state.BowlingSymbol;
import bowling.domain.state.Result;

public class Miss implements Result {
    private final BowlingPin beforePin;
    private final BowlingPin currentPin;

    private Miss(BowlingPin beforePin, BowlingPin currentPin) {
        validate(beforePin, currentPin);
        this.beforePin = beforePin;
        this.currentPin = currentPin;
    }

    public static Miss of(BowlingPin beforePin, BowlingPin currentPin) {
        return new Miss(beforePin, currentPin);
    }

    private void validate(BowlingPin beforePin, BowlingPin currentPin) {
        if (beforePin.sum(currentPin).isMax()) {
            throw new IllegalArgumentException("현재 상태에 맞지 않는 볼링핀 수 입니다.");
        }
    }

    @Override
    public String toSymbol() {
        return BowlingSymbol.of(currentPin);
    }

    @Override
    public Score score() {
        return Score.of(beforePin.sum(currentPin).score());
    }

    @Override
    public int currentBowlingPin() {
        return currentPin.score();
    }

    @Override
    public boolean isClear() {
        return false;
    }
}
