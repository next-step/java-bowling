package bowling.domain.state;

import bowling.domain.KnockedPinCount;

public class Strike extends AbstractFinished {
    public Strike() {
        super(KnockedPinCount.TEN_COUNT);
    }

    @Override
    public State bowl(int pinCount) {
        return Bonus.ofStrike(knockedPinCounts.knockOut(pinCount), this);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean hasBonus() {
        return true;
    }
}
