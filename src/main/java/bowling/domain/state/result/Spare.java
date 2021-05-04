package bowling.domain.state.result;

import bowling.domain.score.Score;
import bowling.domain.state.BowlingPin;
import bowling.domain.state.BowlingSymbol;
import bowling.domain.state.Result;

public class Spare implements Result {
    private final BowlingPin beforePin;
    private final BowlingPin currentPin;

    private Spare(BowlingPin beforePin, BowlingPin currentPin) {
        validate(beforePin, currentPin);
        this.beforePin = beforePin;
        this.currentPin = currentPin;
    }

    public static Spare of(BowlingPin beforePin, BowlingPin currentPin) {
        return new Spare(beforePin, currentPin);
    }

    private void validate(BowlingPin beforePin, BowlingPin currentPin) {
        if (!beforePin.sum(currentPin).isMax()) {
            throw new IllegalArgumentException("현재 상태에 맞지 않는 볼링핀 수 입니다.");
        }
    }

    @Override
    public String toSymbol() {
        return BowlingSymbol.ofSpare();
    }

    @Override
    public Score score() {
        return Score.ofSpare();
    }

    @Override
    public int currentBowlingPin() {
        return currentPin.score();
    }

    @Override
    public boolean isClear() {
        return true;
    }
}
