package bowling.domain.state.result;

import bowling.domain.state.BowlingPin;
import bowling.domain.state.BowlingSymbol;
import bowling.domain.state.Result;

public class Miss implements Result {
    private final BowlingPin firstPin;
    private final BowlingPin secondPin;

    private Miss(BowlingPin firstPin, BowlingPin secondPin) {
        validate(firstPin, secondPin);
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    private void validate(BowlingPin firstPin, BowlingPin secondPin) {
        if (firstPin.sum(secondPin).isMax()) {
            throw new IllegalArgumentException("현재 상태에 맞지 않는 볼링핀 수 입니다.");
        }
    }

    public static Miss of(BowlingPin firstPin, BowlingPin secondPin) {
        return new Miss(firstPin, secondPin);
    }

    @Override
    public boolean isClear() {
        return false;
    }

    @Override
    public String toSymbol() {
        return BowlingSymbol.of(firstPin, secondPin);
    }
}
