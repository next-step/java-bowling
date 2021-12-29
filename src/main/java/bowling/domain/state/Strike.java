package bowling.domain.state;

import bowling.domain.KnockedPinCount;
import bowling.domain.Score;

public class Strike extends AbstractFinished {
    private static final int STRIKE_PIN_COUNT = 10;

    public Strike() {
        super(KnockedPinCount.TEN_COUNT);
    }

    @Override
    public State bowl(int pinCount) {
        return Bonus.ofStrike(pinCounts.knockOut(pinCount), this);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public boolean hasBonus() {
        return true;
    }

    @Override
    public Score makeScore() {
        return Score.ofStrike();
    }

    @Override
    public Score additionalCalculate(Score beforeScore) {
        return beforeScore.bowl(STRIKE_PIN_COUNT);
    }

    @Override
    public boolean isSpare() {
        return false;
    }

    @Override
    public String display() {
        return STRIKE_MARK;
    }
}
