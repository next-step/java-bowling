package bowling.domain.state.result;

import bowling.domain.state.BowlingPin;
import bowling.domain.state.BowlingSymbol;
import bowling.domain.state.Result;

public class Strike implements Result {
    private final BowlingPin bowlingPin;

    private Strike(BowlingPin bowlingPin) {
        validate(bowlingPin);
        this.bowlingPin = bowlingPin;
    }

    private void validate(BowlingPin bowlingPin) {
        if (!bowlingPin.isMax()) {
            throw new IllegalArgumentException("현재 상태에 맞지 않는 볼링핀 수 입니다.");
        }
    }

    public static Strike of(BowlingPin bowlingPin) {
        return new Strike(bowlingPin);
    }

    @Override
    public boolean isClear() {
        return true;
    }

    @Override
    public String toSymbol() {
        return BowlingSymbol.of(bowlingPin);
    }

}
