package bowling.domain.frame;

import bowling.domain.Round;
import bowling.domain.ScoreSymbol;
import bowling.domain.Pin;

public class FinalFrame extends Frame {
    private static final int MAX_FINAL_PIN_COUNT = 30;

    private FinalFrame() { }

    public static FinalFrame newInstance() {
        return new FinalFrame();
    }

    @Override
    public void play(int countOfDownPin) {
        if ((getTotalPinCount() + countOfDownPin) > MAX_FINAL_PIN_COUNT) {
            throw new IllegalArgumentException("마지막 프레임은 핀을 최대 30개까지 쓰러뜨릴 수 있습니다.");
        }
        pins.add(Pin.from(countOfDownPin));
    }

    @Override
    public Boolean isNextFrame() {
        return false;
    }

    @Override
    public boolean isEnd() {
        if (isFirst()) {
            return false;
        }
        if (isSecond() && getFirstPin() == ScoreSymbol.STRIKE.getPin()) {
            return false;
        }
        if (isSecond()) {
            return getTotalPinCount() != Pin.MAX_PIN_COUNT;
        }
        return isBonus();
    }

    @Override
    public Frame next(Round round) {
        return new FinalFrame();
    }
}
