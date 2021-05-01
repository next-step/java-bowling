package bowling.domain.state.result;

import bowling.domain.state.BowlingPin;
import bowling.domain.state.BowlingSymbol;
import bowling.domain.state.Result;

public class Spare implements Result {
    private final BowlingPin secondPin;

    private Spare(BowlingPin secondPin) {
        validate(secondPin);
        this.secondPin = secondPin;
    }

    public static Spare of(BowlingPin secondPin) {
        return new Spare(secondPin);
    }

    private void validate(BowlingPin bowlingPin) {
        if (bowlingPin.isMin()) {
            throw new IllegalArgumentException("현재 상태에 맞지 않는 볼링핀 수 입니다.");
        }
    }

    @Override
    public String toSymbol() {
        return BowlingSymbol.ofSpare();
    }
}
